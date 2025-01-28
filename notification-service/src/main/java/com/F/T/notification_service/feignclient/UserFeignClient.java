package com.F.T.notification_service.feignclient;

import org.example.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/api/v1/user/user-id/{userId}")
    public ResponseEntity<UserDto> findUserByUserId(@PathVariable("userId")String userId);
}
