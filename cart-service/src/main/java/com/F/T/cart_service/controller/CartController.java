package com.F.T.cart_service.controller;

import com.F.T.cart_service.service.CartService;
import org.example.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> createCart(@PathVariable("userId")String userId){
        return ResponseEntity.ok(cartService.createCart(userId));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<CartDto> findCartByUserId(@PathVariable("userId")String userId){
        return ResponseEntity.ok(cartService.findCartByUserId(userId));
    }

    @DeleteMapping("/cart-id/{cartId}")
    public ResponseEntity<String> deleteCartByCartId(@PathVariable("cartId")String cartId){
        cartService.deleteCartByCartId(cartId);
        return ResponseEntity.ok("Cart and cart items deleted successfully...");
    }

}
