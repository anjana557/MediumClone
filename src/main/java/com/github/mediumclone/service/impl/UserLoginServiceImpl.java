package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.RequestDto.UserLoginDto;
import com.github.mediumclone.dto.request.UserLoginRequest;
import com.github.mediumclone.dto.response.UserLoginResponse;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    private final UserRepository userRepository;

    @Override
    public UserLoginResponse createUserLogin(UserLoginRequest userLoginRequest) {
        final UserLoginDto userLoginDto = userLoginRequest.getUser();

        final Optional<User> userOptional = userRepository.findByUsername("anjana");
        if (userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        final User user = userOptional.get();
        user.setEmail(userLoginDto.getEmail());


        return null;
    }
}
