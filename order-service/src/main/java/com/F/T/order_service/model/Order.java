package com.F.T.order_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId;
    private String cartId;
    private List<OrderItem> orderItemList;
    private BigDecimal price;
    private LocalDate creationDate;

    public Order(){
    }

    public Order(String userId,String cartId,List<OrderItem> orderItemList){
        this.userId=userId;
        this.cartId=cartId;
        this.orderItemList=orderItemList;
        this.creationDate=LocalDate.now();
    }

    public String getId(){
        return id;
    }

    public String getUserId(){
        return userId;
    }

    public String getCartId(){
        return cartId;
    }

    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public void calculatePrice(){
        List<BigDecimal> prices=orderItemList
                .stream()
                .map(orderItem -> orderItem.getPrice())
                .toList();
        this.price=BigDecimal.ZERO;
        for (BigDecimal price : prices){
            this.price=this.price.add(price);
        }
    }
}
