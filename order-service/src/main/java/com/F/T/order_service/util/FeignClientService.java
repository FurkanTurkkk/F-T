package com.F.T.order_service.util;

import com.F.T.order_service.feignclient.BookFeignClient;
import com.F.T.order_service.feignclient.CartFeignClient;
import com.F.T.order_service.feignclient.UserFeignClient;
import org.example.BookDto;
import org.example.CartDto;
import org.example.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final BookFeignClient bookFeignClient;
    private final UserFeignClient userFeignClient;
    private final CartFeignClient cartFeignClient;

    public FeignClientService(BookFeignClient bookFeignClient,
                              UserFeignClient userFeignClient,
                              CartFeignClient cartFeignClient) {
        this.bookFeignClient = bookFeignClient;
        this.userFeignClient = userFeignClient;
        this.cartFeignClient = cartFeignClient;
    }

    public String findBookNameByBookId(String bookId){
        BookDto bookDto=bookFeignClient.findBookByBookId(bookId).getBody();
        return bookDto.getBookName();
    }

    public String findBookIdByBookName(String bookName){
        return bookFeignClient.findBookIdByBookName(bookName);
    }

    public String findUserEmailByUserId(String userId){
        UserDto userDto=userFeignClient.findUserByUserId(userId).getBody();
        return userDto.getEmail();
    }

    public CartDto findCartDtoByUserId(String userId){
        return cartFeignClient.findCartByUserId(userId).getBody();
    }
}
