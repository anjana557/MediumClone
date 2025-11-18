package com.github.mediumclone.dto.request;

import com.github.mediumclone.dto.RequestDto.UserLoginDto;
import lombok.Data;

@Data
public class UserLoginRequest {
    public UserLoginDto user;
}
