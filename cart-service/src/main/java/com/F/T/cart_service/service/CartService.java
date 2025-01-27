package com.F.T.cart_service.service;

import com.F.T.cart_service.converter.CartDtoConverter;
import com.F.T.cart_service.exception.CartNotFoundException;
import com.F.T.cart_service.model.Cart;
import com.F.T.cart_service.repository.CartRepository;
import org.example.CartDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDtoConverter converter;
    private final CartManagement cartManagement;

    public CartService(CartRepository cartRepository, CartDtoConverter converter, CartManagement cartManagement) {
        this.cartRepository = cartRepository;
        this.converter = converter;
        this.cartManagement = cartManagement;
    }

    public CartDto createCart(String userId) {
        checkForCreateCart(userId);
        return converter.convert(cartRepository.save(new Cart(userId)));
    }

    public CartDto findCartByUserId(String userId) {
        Cart cart=cartRepository.findByUserId(userId)
                .orElseThrow(()->new CartNotFoundException("Cart could not found by user id : "+userId));
        return converter.convert(cart);
    }

    public Cart findCartByCartId(String cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Cart could not found by id : "+cartId));
    }

    public void saveCart(Cart cart){
        cart.calculatePrice();
        cartRepository.save(cart);
    }

    private void checkForCreateCart(String userId){
        Optional<Cart> registeredCart = cartRepository.findByUserId(userId);
        if(registeredCart.isPresent()){
            throw new RuntimeException("Cart already exist...");
        }
    }

    public void deleteCartByCartId(String cartId) {
        Cart cart=findCartByCartId(cartId);
        cart.getCartItemList()
                        .forEach(cartItem -> cartManagement.getCartItemService().deleteCartItemByCartId(cartId));
        cartRepository.delete(cart);
    }
}
