package com.github.mediumclone.dto.responseDto;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    String email;
    String token;
    String username;
    String bio;
    String image;
}
