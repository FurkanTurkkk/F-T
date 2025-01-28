package com.F.T.notification_service.util;

import com.F.T.notification_service.feignclient.UserFeignClient;
import org.example.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final UserFeignClient userFeignClient;

    public FeignClientService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public String findUserMailByUserId(String userId){
        UserDto userDto=userFeignClient.findUserByUserId(userId).getBody();
        return userDto.getEmail();
    }
}
