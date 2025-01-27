package com.F.T.cart_service.converter;

import com.F.T.cart_service.model.Cart;
import org.example.CartDto;
import org.example.CartItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartDtoConverter {
    private final CartItemDtoConverter cartItemDtoConverter;

    public CartDtoConverter(CartItemDtoConverter cartItemDtoConverter) {
        this.cartItemDtoConverter = cartItemDtoConverter;
    }

    public CartDto convert(Cart cart){
        List<CartItemDto> cartItemDtoList=cart.getCartItemList()
                .stream()
                .map(cartItemDtoConverter::convert)
                .toList();
        return new CartDto(
                cartItemDtoList,
                cart.getPrice()
        );
    }
}
