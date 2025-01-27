package com.F.T.cart_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "cart-items")
public class CartItem {

    @Id
    private String id;

    private String cartId;
    private String productId;
    private int quantity;
    private BigDecimal price;

    public CartItem(){

    }

    public CartItem(String cartId,String productId,int quantity){
        this.cartId=cartId;
        this.productId=productId;
        this.quantity=quantity;
    }

    public String getId(){
        return id;
    }

    public String getCartId(){
        return cartId;
    }

    public String getProductId(){
        return productId;
    }

    public int getQuantity(){
        return quantity;
    }

    public BigDecimal getPrice(){
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(productId, cartItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    public void calculatePrice(BigDecimal bookPrice){
        this.price=BigDecimal.ZERO;
        this.price=bookPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public int updateQuantity(int quantity){
        return this.quantity=quantity;
    }

}
