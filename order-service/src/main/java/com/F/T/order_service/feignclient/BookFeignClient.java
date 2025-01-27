package com.F.T.order_service.feignclient;

import org.example.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service")
public interface BookFeignClient {

    @GetMapping("/api/v1/book/book-id/{bookId}")
    public ResponseEntity<BookDto> findBookByBookId(@PathVariable("bookId") String bookId);

    @GetMapping("/api/v1/book/get-book-id/book-name/{bookName}")
    public String findBookIdByBookName(@PathVariable("bookName")String bookName);
}
