package com.github.mediumclone.controller;

import com.github.mediumclone.dto.request.UserLoginRequest;
import com.github.mediumclone.dto.response.UserLoginResponse;
import com.github.mediumclone.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;
    @PostMapping("/users/login")
    public UserLoginResponse createUserLogin(@RequestBody UserLoginRequest userLoginRequest){
        return userLoginService.createUserLogin(userLoginRequest) ;
    }
}
