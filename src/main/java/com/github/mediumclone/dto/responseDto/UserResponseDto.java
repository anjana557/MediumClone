package com.github.mediumclone.dto.responseDto;

import lombok.Data;

@Data
public class UserResponseDto {
    String email;
    String token;
    String username;
    String bio;
    String image;


}
