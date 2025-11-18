package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.RequestDto.UserDto;
import com.github.mediumclone.dto.RequestDto.UserUpdateDto;
import com.github.mediumclone.dto.request.ArticleRequest;
import com.github.mediumclone.dto.request.UserRequest;
import com.github.mediumclone.dto.request.UserUpdate;
import com.github.mediumclone.dto.response.UserResponse;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.exception.UserNotFoundException;
import com.github.mediumclone.mapper.UserMapper;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        final UserDto user1 = userRequest.getUser();
        User user = new User(user1.getEmail(), user1.getUsername(),user1.getPassword());
        final User saveUser = userRepository.save(user);
        return UserMapper.map(saveUser);
    }

    @Override
    public UserResponse getUser(String username) {

        final Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        final User user = userOptional.get();
       return  UserMapper.map(user);
    }

    @Override
    public UserResponse updateUser(UserUpdate userUpdate) {
        final UserUpdateDto userUpdateDto = userUpdate.getUser();
        final Optional<User> userOptional = userRepository.findByUsername(userUpdateDto.getUsername());
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        final User user = userOptional.get();
        user.setBio(userUpdateDto.getBio());
        user.setEmail(userUpdateDto.getEmail());
        user.setImageUrl(userUpdateDto.getImage());
        userRepository.save(user);
        return UserMapper.map(user);
    }


}
