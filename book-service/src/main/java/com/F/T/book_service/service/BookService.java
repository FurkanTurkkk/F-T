package com.F.T.book_service.service;

import com.F.T.book_service.converter.DtoConverter;
import com.F.T.book_service.exception.BookAlreadyExistException;
import com.F.T.book_service.exception.BookNotFoundException;
import com.F.T.book_service.model.Book;
import com.F.T.book_service.repository.BookRepository;
import com.F.T.book_service.request.RequestForCreateBook;
import com.F.T.book_service.util.FeignClientService;
import feign.FeignException;
import org.example.BookDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final DtoConverter converter;
    private final FeignClientService feignClientService;

    public BookService(BookRepository bookRepository, DtoConverter converter, FeignClientService feignClientService) {
        this.bookRepository = bookRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
    }

    @Transactional(rollbackFor = Exception.class)
    public BookDto createBook(RequestForCreateBook request) {
        checkForCreateBook(request);
        Book book = new Book(
                request.getBookName(),
                request.getAuthor(),
                request.getPageCount(),
                request.getExplanation(),
                request.getCategoryIdList(),
                request.getPrice(),
                request.getStock()
        );
        try {
            bookRepository.save(book);
            book.getCategoryId()
                    .forEach(t->feignClientService.addBookToCategory(t,book.getId()));
        }catch (FeignException e){
            bookRepository.delete(book);
        }
        catch (Exception e){

            throw new RuntimeException("Bir hata oluştu "+e.getMessage(),e);
        }

        return converter.convert(book);
    }

    public BookDto findBookByBookId(String bookId) {
        return converter.convert(checkBookByBookId(bookId));
    }

    public BookDto findBookByBookName(String bookName){
        return converter.convert(checkBookByBookName(bookName));
    }

    public BookDto findBookByAuthorName(String authorName) {
        Book book=checkBookByAuthor(authorName);
        return converter.convert(book);
    }

    public String findBookIdByBookName(String bookName) {
        Book book=bookRepository.findByBookName(bookName)
                .orElseThrow(()->new BookNotFoundException("Book could not found by book name : "+bookName));
        return book.getId();
    }

    public void deleteBookById(String bookId) {
        Book book=checkBookByBookId(bookId);
        book.getCategoryId()
                .forEach(categoryId-> feignClientService.deleteBookToCategory(categoryId,bookId));
        bookRepository.deleteById(bookId);
    }

    public void deleteAllBook() {
        List<Book> bookList=bookRepository.findAll();
        bookList.forEach(book -> {
            book.getCategoryId().forEach(
                    categoryId-> feignClientService.deleteBookToCategory(categoryId,book.getId()));
        });
        bookRepository.deleteAll();
    }

    private Book checkBookByAuthor(String author){
        Optional<Book> registeredBook = bookRepository.findByAuthor(author);
        if(registeredBook.isEmpty()){
            throw new BookNotFoundException("Book could not found by author : "+author);
        }
        return registeredBook.get();
    }

    private Book checkBookByBookId(String bookId){
        Optional<Book> registeredBook = bookRepository.findById(bookId);
        if(registeredBook.isEmpty()){
            throw new BookNotFoundException("Book could not found by id : "+bookId);
        }
        return registeredBook.get();
    }

    private Book checkBookByBookName(String bookName){
        Optional<Book> registeredBook = bookRepository.findByBookName(bookName);
        if(registeredBook.isEmpty()){
            throw new BookNotFoundException("Book could not found by id : "+bookName);
        }
        return registeredBook.get();
    }

    private void checkForCreateBook(RequestForCreateBook request){
        Optional<Book> registeredBook = bookRepository.findByBookName(request.getBookName());
        if(registeredBook.isPresent()){
            throw new BookAlreadyExistException("Book already exist by book name : "+request.getBookName());
        }
    }

}
