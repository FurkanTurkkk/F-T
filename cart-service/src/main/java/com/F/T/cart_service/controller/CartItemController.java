package com.F.T.cart_service.controller;

import com.F.T.cart_service.service.CartItemService;
import org.example.CartItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-item")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/cart-id/{cartId}/book-id/{bookId}")
    public ResponseEntity<CartItemDto> createCartItem(@PathVariable("cartId")String cartId,
                                                      @PathVariable("bookId")String bookId,
                                                      @RequestBody int quantity){
        return ResponseEntity.ok(cartItemService.createCartItem(cartId,bookId,quantity));
    }

    @DeleteMapping("cart-item-id/{cartItemId}")
    public ResponseEntity<String> deleteCartItemById(@PathVariable("cartItemId")String cartItemId){
        cartItemService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok("Cart Item deleted successfully...");
    }
}
