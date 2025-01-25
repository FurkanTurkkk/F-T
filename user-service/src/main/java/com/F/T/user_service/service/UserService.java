package com.F.T.user_service.service;

import com.F.T.user_service.converter.DtoConverter;
import com.F.T.user_service.exception.UserAlreadyExistException;
import com.F.T.user_service.model.User;
import com.F.T.user_service.repository.UserRepository;
import com.F.T.user_service.request.RequestForCreateUser;
import com.F.T.user_service.request.RequestForUpdateUser;
import jakarta.ws.rs.NotFoundException;
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
        checkUserByEmailAndPhoneNumber(request.getEmail(),request.getPhoneNumber());
        User user=new User(request.getFirstname(),
                request.getLastname(),
                request.getTc(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getBirthday());
        userRepository.save(user);
        return converter.convert(user);
    }

    public UserDto updateUser(String userID,RequestForUpdateUser request) {
        User user=checkUserById(userID);
        checkUserByEmailAndPhoneNumber(request.getEmail(),request.getPhoneNumber());
        user.updateUser(request.getEmail(),request.getPhoneNumber());
        return converter.convert(userRepository.save(user));
    }

    public UserDto findUserById(String userID){
        return converter.convert(checkUserById(userID));
    }

    public void deleteUserById(String userID) {
        userRepository.deleteById(userID);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    private void checkUserByEmailAndPhoneNumber(String email,String phoneNumber){
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email already exist : "+email);
        }
        if(userRepository.findByPhoneNumber(phoneNumber).isPresent()){
            throw new RuntimeException("Phone number already exist : "+phoneNumber);
        }
    }

    private User checkUserById(String userID){
        Optional<User> registeredUser=userRepository.findById(userID);
        if(registeredUser.isEmpty()){
            throw new NotFoundException("User could not found by id : "+userID);
        }
        return registeredUser.get();
    }

}
