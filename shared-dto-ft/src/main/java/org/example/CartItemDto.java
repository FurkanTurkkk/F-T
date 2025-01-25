package org.example;

import java.math.BigDecimal;

public class CartItemDto {

    private String bookName;
    private int quantity;
    private BigDecimal price;

    public CartItemDto(){

    }

    public CartItemDto(String bookName,int quantity,BigDecimal price){
        this.bookName=bookName;
        this.quantity=quantity;
        this.price=price;
    }

    public String getBookName(){
        return bookName;
    }

    public int getQuantity(){
        return quantity;
    }

    public BigDecimal getPrice(){
        return price;
    }
}
