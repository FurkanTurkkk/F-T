package com.F.T.order_service.service;

import com.F.T.order_service.converter.OrderDtoConverter;
import com.F.T.order_service.model.Order;
import com.F.T.order_service.model.OrderItem;
import com.F.T.order_service.repository.OrderRepository;
import com.F.T.order_service.util.FeignClientService;
import org.example.CartDto;
import org.example.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;
    private final FeignClientService feignClientService;

    public OrderService(OrderRepository orderRepository, OrderDtoConverter orderDtoConverter, FeignClientService feignClientService) {
        this.orderRepository = orderRepository;
        this.converter = orderDtoConverter;
        this.feignClientService = feignClientService;
    }

    public OrderDto createOrder(String userId, String cartId) {
        CartDto cartDto =feignClientService.findCartDtoByUserId(userId);
        List<OrderItem> orderItemList=cartDto.getCartItemList()
                .stream()
                .map(cartItemDto -> {
                    String productId = feignClientService.findBookIdByBookName(cartItemDto.getBookName());
                    OrderItem orderItem=new OrderItem(productId,cartItemDto.getQuantity());
                    orderItem.calculatePrice(cartItemDto.getPrice());
                    return orderItem;
                }).toList();
        Order order=new Order(userId,cartId,orderItemList);
        return converter.convert(saveOrder(order));
    }

    private Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
