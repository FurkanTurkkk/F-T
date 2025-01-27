package com.F.T.order_service.converter;

import com.F.T.order_service.model.OrderItem;
import com.F.T.order_service.util.FeignClientService;
import org.example.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoConverter {

    private final FeignClientService feignClientService;

    public OrderItemDtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public OrderItemDto convert(OrderItem orderItem){
        String bookName= feignClientService.findBookNameByBookId(orderItem.getProductId());
        return new OrderItemDto(
                bookName,
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
