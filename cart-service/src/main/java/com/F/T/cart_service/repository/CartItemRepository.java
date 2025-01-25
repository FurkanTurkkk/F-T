package com.F.T.cart_service.repository;

import com.F.T.cart_service.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem,String> {
    Optional<CartItem> findByProductId(String productId);
}
