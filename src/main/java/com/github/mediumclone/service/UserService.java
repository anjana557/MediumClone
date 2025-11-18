package com.github.mediumclone.service;

import com.github.mediumclone.dto.request.ArticleRequest;
import com.github.mediumclone.dto.request.UserRequest;
import com.github.mediumclone.dto.request.UserUpdate;
import com.github.mediumclone.dto.response.UserResponse;
import com.github.mediumclone.mapper.UserMapper;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUser(String username);
    UserResponse updateUser(UserUpdate userUpdate);

}
