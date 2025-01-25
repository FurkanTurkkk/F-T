package com.F.T.book_service.converter;

import com.F.T.book_service.model.Book;
import com.F.T.book_service.util.FeignClientService;
import org.example.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoConverter {

    private final FeignClientService feignClientService;

    public DtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public BookDto convert(Book book){
        List<String> categoryNameList=feignClientService.findCategoryNameByCategoryId(book);
        return new BookDto(
                book.getBookName(),
                book.getAuthor(),
                categoryNameList,
                book.getPageCount(),
                book.getExplanation(),
                book.getPrice(),
                book.getStock()
        );
    }
}
