package com.F.T.user_service.converter;

import com.F.T.user_service.model.User;
import org.example.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DtoConverter {

    public UserDto convert(User user){
        int age= LocalDate.now().getYear()-user.getBirthday().getYear();
        return new UserDto(
                user.getFirstname(),
                user.getLastname(),
                user.getTc(),
                user.getEmail(),
                user.getPhoneNumber(),
                age
        );
    }
}
