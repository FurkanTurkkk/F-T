package com.F.T.cart_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    private String userId;

    private List<CartItem> cartItemList;

    private BigDecimal price;
    private LocalDate creationDate;

    public Cart(){

    }

    public Cart(String userId){
        this.userId=userId;
        this.cartItemList=new ArrayList<>();
        this.creationDate=LocalDate.now();
    }

    public String getId(){
        return id;
    }

    public String getUserId(){
        return userId;
    }

    public List<CartItem> getCartItemList(){
        return cartItemList;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(userId, cart.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    public void calculatePrice(){
        List<BigDecimal> totalPrice=this.cartItemList.stream().map(CartItem::getPrice).toList();
        this.price=BigDecimal.ZERO;
        for (BigDecimal price:totalPrice){
            this.price=this.price.add(price);
        }
    }


}
