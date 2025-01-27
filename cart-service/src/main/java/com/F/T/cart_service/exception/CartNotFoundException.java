package com.F.T.cart_service.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String s) {
        super(s);
    }
}
