package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.UserResponse;
import com.github.mediumclone.dto.responseDto.UserResponseDto;
import com.github.mediumclone.entity.User;

import java.util.List;
import java.util.UUID;

public class UserMapper {

   public  static UserResponse map(User saveUser){
      UserResponse userResponse = new UserResponse();
      UserResponseDto userResponseDto = new UserResponseDto();
      userResponseDto.setEmail(saveUser.getEmail());
      userResponseDto.setToken(null);
      userResponseDto.setUsername(saveUser.getUsername());
      userResponseDto.setBio(saveUser.getBio());
      userResponseDto.setImage(saveUser.getImageUrl());

      userResponse.setUser(userResponseDto);
      return userResponse;

   }
}
