package com.github.mediumclone.dto.RequestDto;

import lombok.Data;

@Data
public class UserUpdateDto {
    String email;
    String password;
    String username;
    String bio;
    String image;
}
