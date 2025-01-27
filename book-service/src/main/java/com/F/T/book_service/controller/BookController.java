package com.F.T.book_service.controller;

import com.F.T.book_service.request.RequestForCreateBook;
import com.F.T.book_service.service.BookService;
import org.example.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody RequestForCreateBook request){
        return ResponseEntity.ok(bookService.createBook(request));
    }

    @GetMapping("/book-id/{bookId}")
    public ResponseEntity<BookDto> findBookByBookId(@PathVariable("bookId") String bookId){
        return ResponseEntity.ok(bookService.findBookByBookId(bookId));
    }

    @GetMapping("/book-name")
    public ResponseEntity<BookDto> findBookByBookName(@RequestBody String bookName){
        return ResponseEntity.ok(bookService.findBookByBookName(bookName));
    }

    @GetMapping("/author")
    public ResponseEntity<BookDto> findBookByAuthorName(@RequestBody String authorName){
        return ResponseEntity.ok(bookService.findBookByAuthorName(authorName));
    }

    @GetMapping("/get-book-id/book-name/{bookName}")
    public String findBookIdByBookName(@PathVariable("bookName")String bookName){
        return bookService.findBookIdByBookName(bookName);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId")String bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book deleted successfully by id : "+bookId);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllBook(){
        bookService.deleteAllBook();
        return ResponseEntity.ok("Books deleted successfully ... ");
    }
}
