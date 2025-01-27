package com.F.T.order_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "order-items")
public class OrderItem {

    @Id
    private String id;

    private String orderId;
    private String productId;
    private int quantity;
    private BigDecimal price;

    public OrderItem(){

    }

    public OrderItem(String productId,int quantity){
        this.productId=productId;
        this.quantity=quantity;
    }

    public String getId(){
        return id;
    }

    public String getOrderId(){
        return orderId;
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

    public void calculatePrice(BigDecimal orderItemPrice){
        this.price=orderItemPrice;
    }
}
