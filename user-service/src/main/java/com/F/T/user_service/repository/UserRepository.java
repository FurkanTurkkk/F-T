package com.F.T.user_service.repository;

import com.F.T.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmailAndPhoneNumber(String email,String phoneNumber);
}
