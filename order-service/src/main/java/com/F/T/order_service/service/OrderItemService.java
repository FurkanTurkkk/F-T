package com.F.T.order_service.service;

import com.F.T.order_service.model.OrderItem;
import com.F.T.order_service.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
