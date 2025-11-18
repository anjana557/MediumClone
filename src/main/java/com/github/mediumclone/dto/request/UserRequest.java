package com.github.mediumclone.dto.request;

import com.github.mediumclone.dto.RequestDto.UserDto;
import lombok.Data;

@Data
public class UserRequest {
    private UserDto user;
}