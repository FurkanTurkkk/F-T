package com.F.T.cart_service.service;

import com.F.T.cart_service.converter.DtoConverter;
import com.F.T.cart_service.model.CartItem;
import com.F.T.cart_service.repository.CartItemRepository;
import com.F.T.cart_service.util.FeignClientService;
import org.example.CartItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final FeignClientService feignClientService;
    private final DtoConverter converter;

    public CartItemService(CartItemRepository cartItemRepository,
                           FeignClientService feignClientService,
                           DtoConverter converter) {
        this.cartItemRepository = cartItemRepository;
        this.feignClientService = feignClientService;
        this.converter = converter;
    }

    @Transactional
    public CartItemDto createCartItem(String cartId,String bookId, int quantity) {
        Optional<CartItem> registeredCartItem = cartItemRepository.findByProductId(bookId);
        feignClientService.checkForStock(bookId,quantity);
        BigDecimal bookPrice=feignClientService.findBookPriceByBookId(bookId);
        if(registeredCartItem.isPresent()){
            CartItem existingCartItem=registeredCartItem.get();
            existingCartItem.updateQuantity(quantity);
            existingCartItem.calculatePrice(bookPrice);
            return converter.convert(cartItemRepository.save(existingCartItem));
        }
        CartItem cartItem=new CartItem(cartId,bookId,quantity);
        cartItem.calculatePrice(bookPrice);
        cartItemRepository.save(cartItem);
        return converter.convert(cartItem);
    }

    public void deleteCartItemById(String cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
