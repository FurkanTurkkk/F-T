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
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository,
                        OrderDtoConverter orderDtoConverter,
                        FeignClientService feignClientService,
                        OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.converter = orderDtoConverter;
        this.feignClientService = feignClientService;
        this.orderItemService = orderItemService;
    }

    public OrderDto createOrder(String userId, String cartId) {
        CartDto cartDto =feignClientService.findCartDtoByUserId(userId);
        List<OrderItem> orderItemList=cartDto.getCartItemList()
                .stream()
                .map(cartItemDto -> {
                    String productId = feignClientService.findBookIdByBookName(cartItemDto.getBookName());
                    OrderItem orderItem=new OrderItem(productId,cartItemDto.getQuantity());
                    orderItem.calculatePrice(cartItemDto.getPrice());
                    orderItemService.saveOrderItem(orderItem);
                    return orderItem;
                }).toList();
        Order order=new Order(userId,cartId,orderItemList);
        order.calculatePrice();
        orderRepository.save(order);
        return converter.convert(order);

    }

    private Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
