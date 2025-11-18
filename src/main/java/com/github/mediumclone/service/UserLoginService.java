package com.github.mediumclone.service;

import com.github.mediumclone.dto.request.UserLoginRequest;
import com.github.mediumclone.dto.response.UserLoginResponse;

public interface UserLoginService {
    UserLoginResponse createUserLogin(UserLoginRequest userLoginRequest);
}
