package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private BigDecimal totalPrice;
    private List<OrderItemDto> orderItemDtoList;
    private String userEmailAddress;
    private LocalDate creationDate;

    public OrderDto(){

    }

    public OrderDto(BigDecimal totalPrice,
                    List<OrderItemDto> orderItemDtoList,
                    String userEmailAddress,
                    LocalDate creationDate){
        this.totalPrice=totalPrice;
        this.orderItemDtoList=orderItemDtoList;
        this.userEmailAddress=userEmailAddress;
        this.creationDate=creationDate;
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

    public LocalDate getCreationDate(){
        return creationDate;
    }
}
