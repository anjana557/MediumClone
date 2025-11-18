package com.github.mediumclone.service;

import com.github.mediumclone.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse followProfile(String currentUsername,String username);
    ProfileResponse getProfile(String username);
    ProfileResponse deleteProfile(String currentUsername, String username);
}
