package com.F.T.user_service.controller;

import com.F.T.user_service.request.RequestForCreateUser;
import com.F.T.user_service.request.RequestForUpdateUser;
import com.F.T.user_service.service.UserService;
import org.example.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody RequestForCreateUser request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId,
                                              @RequestBody RequestForUpdateUser request){
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<UserDto> findUserByUserId(@PathVariable("userId")String userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }*/

}
