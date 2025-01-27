package org.example;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private BigDecimal totalPrice;
    private List<OrderItemDto> orderItemDtoList;
    private String userEmailAddress;

    public OrderDto(){

    }

    public OrderDto(BigDecimal totalPrice,List<OrderItemDto> orderItemDtoList,String userEmailAddress){
        this.totalPrice=totalPrice;
        this.orderItemDtoList=orderItemDtoList;
        this.userEmailAddress=userEmailAddress;
    }

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    public List<OrderItemDto> getOrderItemDtoList(){
        return orderItemDtoList;
    }

    public String getUserEmailAddress(){
        return userEmailAddress;
    }
}
