package com.F.T.cart_service.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CartManagement {

    private final CartService cartService;
    private final CartItemService cartItemService;

    public CartManagement(@Lazy CartService cartService, @Lazy CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public CartItemService getCartItemService() {
        return cartItemService;
    }
}
