package com.F.T.order_service.util;

import com.F.T.order_service.feignclient.BookFeignClient;
import com.F.T.order_service.feignclient.CartFeignClient;
import com.F.T.order_service.feignclient.NotificationFeignClient;
import com.F.T.order_service.feignclient.UserFeignClient;
import com.F.T.order_service.request.RequestForSendMailForSuccessOrder;
import org.example.BookDto;
import org.example.CartDto;
import org.example.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final BookFeignClient bookFeignClient;
    private final UserFeignClient userFeignClient;
    private final CartFeignClient cartFeignClient;
    private final NotificationFeignClient notificationFeignClient;

    public FeignClientService(BookFeignClient bookFeignClient,
                              UserFeignClient userFeignClient,
                              CartFeignClient cartFeignClient,
                              NotificationFeignClient notificationFeignClient) {
        this.bookFeignClient = bookFeignClient;
        this.userFeignClient = userFeignClient;
        this.cartFeignClient = cartFeignClient;
        this.notificationFeignClient = notificationFeignClient;
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

    public String findUserNameByUserId(String userId){
        UserDto userDto=userFeignClient.findUserByUserId(userId).getBody();
        return userDto.getFirstname()+" "+userDto.getLastname();
    }

    public CartDto findCartDtoByUserId(String userId){
        return cartFeignClient.findCartByUserId(userId).getBody();
    }

    public void deleteCartByCartId(String cartId){
        cartFeignClient.deleteCartByCartId(cartId);
    }

    public void sendMailForSuccess(RequestForSendMailForSuccessOrder request){
        notificationFeignClient.sendMailToUserForOrderSuccess(request);
    }
}
