package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.ProfileResponse;
import com.github.mediumclone.dto.responseDto.ProfileResponseDto;
import com.github.mediumclone.entity.User;

public class ProfileMapper {
    public static ProfileResponse profileMap(User user, boolean isFollowing){
        ProfileResponse profileResponse = new ProfileResponse();
        ProfileResponseDto profile = new ProfileResponseDto();
        profile.setUsername(user.getUsername());
        profile.setBio(user.getBio());
        profile.setImage(user.getImageUrl());
        profile.setFollowing(isFollowing);
        profileResponse.setProfile(profile);
        return profileResponse;
    }
}
