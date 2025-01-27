package com.F.T.cart_service.service;

import com.F.T.cart_service.converter.CartItemDtoConverter;
import com.F.T.cart_service.model.Cart;
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
    private final CartItemDtoConverter converter;
    private final CartManagement cartManagement;

    public CartItemService(CartItemRepository cartItemRepository,
                           FeignClientService feignClientService,
                           CartItemDtoConverter converter,
                           CartManagement cartManagement) {
        this.cartItemRepository = cartItemRepository;
        this.feignClientService = feignClientService;
        this.converter = converter;
        this.cartManagement = cartManagement;
    }

    @Transactional
    public CartItemDto createCartItem(String cartId,String bookId, int quantity) {
        Cart cart = cartManagement.getCartService().findCartByCartId(cartId);
        BigDecimal bookPrice=feignClientService.findBookPriceByBookId(bookId);
        Optional<CartItem> registeredCartItem = cartItemRepository.findByProductId(bookId);
        if(registeredCartItem.isPresent()){
            CartItem existingCartItem = registeredCartItem.get();
            existingCartItem.updateQuantity(quantity);
            existingCartItem.calculatePrice(bookPrice);
            cart.getCartItemList().removeIf(item->item.getProductId().equals(bookId));
            feignClientService.checkForStock(bookId,quantity);
            cart.getCartItemList().add(existingCartItem);
            cart.calculatePrice();
            cartManagement.getCartService().saveCart(cart);
            return converter.convert(cartItemRepository.save(existingCartItem));
        }
        CartItem cartItem=new CartItem(cartId,bookId,quantity);
        cartItem.calculatePrice(bookPrice);
        feignClientService.checkForStock(bookId,quantity);
        cart.getCartItemList().add(cartItem);
        cart.calculatePrice();
        cartManagement.getCartService().saveCart(cart);
        return converter.convert(cartItemRepository.save(cartItem));
    }

    public void deleteCartItemByCartItemId(String cartItemId) {
        CartItem cartItem=cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("Cart Item could not found ! "));
        String cartId=cartItem.getCartId();
        Cart cart=cartManagement.getCartService().findCartByCartId(cartId);
        cart.getCartItemList().remove(cartItem);
        cartManagement.getCartService().saveCart(cart);
        cartItemRepository.delete(cartItem);
    }

    public void deleteCartItemByCartId(String cartId){
        cartItemRepository.deleteByCartId(cartId);
    }
}
