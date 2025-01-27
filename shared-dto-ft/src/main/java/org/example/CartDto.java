package org.example;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private List<CartItemDto> cartItemList;
    private BigDecimal price;

    public CartDto(){

    }

    public CartDto(List<CartItemDto> cartItemList,BigDecimal price){
        this.cartItemList=cartItemList;
        this.price=price;
    }

    public List<CartItemDto> getCartItemList(){
        return cartItemList;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
