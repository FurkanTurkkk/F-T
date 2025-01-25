package com.F.T.cart_service.util;

import com.F.T.cart_service.feignclient.BookFeignClient;
import org.example.BookDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeignClientService {

    private final BookFeignClient bookFeignClient;

    public FeignClientService(BookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }

    public BookDto findBookNameByBookId(String bookId){
        return bookFeignClient.findBookByBookId(bookId).getBody();
    }

    public BigDecimal findBookPriceByBookId(String bookId){
        BookDto bookDto=bookFeignClient.findBookByBookId(bookId).getBody();
        return bookDto.getPrice();
    }

    public void checkForStock(String bookId,int quantity){
        BookDto bookDto=bookFeignClient.findBookByBookId(bookId).getBody();
        Long stock=bookDto.getStock();
        if(quantity>stock){
            throw new RuntimeException("Do not have stock for this book : "+bookId);
        }
    }
}
