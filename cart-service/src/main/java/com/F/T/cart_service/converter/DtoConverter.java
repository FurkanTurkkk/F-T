package com.F.T.cart_service.converter;

import com.F.T.cart_service.model.CartItem;
import com.F.T.cart_service.util.FeignClientService;
import org.example.CartItemDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    private final FeignClientService feignClientService;

    public DtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public CartItemDto convert(CartItem cartItem){
        String bookName=feignClientService.findBookNameByBookId(cartItem.getProductId()).getBookName();
        return new CartItemDto(
                bookName,
                cartItem.getQuantity(),
                cartItem.getPrice()
        );
    }
}
