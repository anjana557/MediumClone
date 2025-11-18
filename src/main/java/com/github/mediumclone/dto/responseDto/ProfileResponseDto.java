package com.github.mediumclone.dto.responseDto;

import lombok.Data;

@Data
public class ProfileResponseDto {
    String username;
    String bio;
    String image;
    boolean following;
}
