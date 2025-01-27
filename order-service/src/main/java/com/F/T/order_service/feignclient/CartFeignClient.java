package com.F.T.order_service.feignclient;

import org.example.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartFeignClient {

    @GetMapping("/api/v1/cart/user-id/{userId}")
    public ResponseEntity<CartDto> findCartByUserId(@PathVariable("userId")String userId);
}
