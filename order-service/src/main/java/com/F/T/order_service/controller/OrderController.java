package com.F.T.order_service.controller;

import com.F.T.order_service.service.OrderService;
import org.example.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/user-id/{userId}/cart-id/{cartId}")
    public ResponseEntity<OrderDto> confirmCart(@PathVariable("userId")String userId,
                                                @PathVariable("cartId")String cartId){
        return ResponseEntity.ok(orderService.createOrder(userId,cartId));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<List<OrderDto>> findOrderListByUserId(@PathVariable("userId")String userId){
        return ResponseEntity.ok(orderService.findOrderListByUserId(userId));
    }
}
