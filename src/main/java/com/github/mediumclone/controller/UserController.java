package com.github.mediumclone.controller;

import com.github.mediumclone.dto.request.UserRequest;
import com.github.mediumclone.dto.request.UserUpdate;
import com.github.mediumclone.dto.response.UserResponse;
import com.github.mediumclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users")
    public UserResponse createUSer(@RequestBody UserRequest userRequest){

        return userService.createUser(userRequest);
    }

    @GetMapping("/user")
    public UserResponse getUser(){
        return userService.getUser("anjana");
    }

    @PutMapping("/users")
    public UserResponse updateUser(@RequestBody UserUpdate userUpdate){
        return userService.updateUser(userUpdate);
    }
}
