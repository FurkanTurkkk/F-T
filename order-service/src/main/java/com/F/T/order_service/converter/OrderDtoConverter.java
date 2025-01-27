package com.F.T.order_service.converter;

import com.F.T.order_service.model.Order;
import com.F.T.order_service.util.FeignClientService;
import org.example.OrderDto;
import org.example.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoConverter {

    private final OrderItemDtoConverter converter;
    private final FeignClientService feignClientService;

    public OrderDtoConverter(OrderItemDtoConverter converter, FeignClientService feignClientService) {
        this.converter = converter;
        this.feignClientService = feignClientService;
    }

    public OrderDto convert(Order order){
        List<OrderItemDto> orderItemDtoList=order.getOrderItemList()
                .stream()
                .map(orderItem -> converter.convert(orderItem))
                .toList();
        String userEmail= feignClientService.findUserEmailByUserId(order.getUserId());
        return new OrderDto(
                order.getPrice(),
                orderItemDtoList,
                userEmail
        );
    }
}
