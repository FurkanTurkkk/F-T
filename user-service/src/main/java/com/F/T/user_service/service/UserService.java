package com.F.T.user_service.service;

import com.F.T.user_service.converter.DtoConverter;
import com.F.T.user_service.exception.UserAlreadyExistException;
import com.F.T.user_service.model.User;
import com.F.T.user_service.repository.UserRepository;
import com.F.T.user_service.request.RequestForCreateUser;
import org.example.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DtoConverter converter;

    public UserService(UserRepository userRepository, DtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserDto createUser(RequestForCreateUser request) {
        return converter.convert(saveUser(request));
    }

    private User saveUser(RequestForCreateUser request){
        Optional<User> registeredUser=userRepository.findByEmailAndPhoneNumber(request.getEmail(),
                request.getPhoneNumber());
        if(registeredUser.isPresent()){
            throw new UserAlreadyExistException("User already exist by email : "+ request.getEmail()
            +" or phone number : "+request.getPhoneNumber());
        }
        return userRepository.save(new User(request.getFirstname(),
                request.getLastname(),
                request.getTc(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getBirthday()));
    }
}
