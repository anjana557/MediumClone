package com.github.mediumclone.controller;

import com.github.mediumclone.dto.response.ProfileResponse;
import com.github.mediumclone.service.ProfileService;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class ProfileController {

    private final ProfileService profileService;
    @Transactional
    @PostMapping("/profiles/{username}/follow")
    public ProfileResponse createProfile(@PathVariable String username, @RequestParam String currentUsername){
        return  profileService.followProfile(currentUsername,username);
    }
    @Transactional
    @GetMapping("/profiles/{username}")
    public ProfileResponse getProfile(@PathVariable String username){
        return profileService.getProfile(username);
    }

    @DeleteMapping("/profiles/{username}/follow")
    public ProfileResponse deleteProfile(@PathVariable String username, @RequestParam String currentUsername){
        return profileService.deleteProfile(currentUsername, username);
    }
}
