package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.response.ProfileResponse;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.entity.UserFollow;
import com.github.mediumclone.exception.ProfileNotFoundException;
import com.github.mediumclone.exception.UserNotFoundException;
import com.github.mediumclone.mapper.ProfileMapper;
import com.github.mediumclone.repository.UserFollowRepository;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private  final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    @Override
    public ProfileResponse followProfile(String currentUsername,String username) {
        final Optional<User> userOptional = userRepository.findByUsername(currentUsername);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        final User currentUser = userOptional.get();

        final Optional<User> userOptional1 = userRepository.findByUsername(username);
        if (userOptional1.isEmpty()){
            throw new ProfileNotFoundException("profile not found.");
        }
        final User followingUser = userOptional1.get();


        final boolean b = userFollowRepository.existsByFollowerAndFollowing(currentUser, followingUser);
        if (!b){
            UserFollow userFollow = new UserFollow(currentUser, followingUser);
            userFollowRepository.save(userFollow);
        }

        return ProfileMapper.profileMap(followingUser, true);
    }

    @Override
    public ProfileResponse getProfile(String username) {
        final Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            throw new ProfileNotFoundException("profile not found.");
        }
        final User user = userOptional.get();
        final boolean present = userRepository.findByUsername("anjana").isPresent();
        if(!present) {
            throw new ProfileNotFoundException("profile not found.");
        }
        return ProfileMapper.profileMap(user,present);

    }

    @Override
    public ProfileResponse deleteProfile(String currentUsername,String username) {
        final Optional<User> userOptional = userRepository.findByUsername(currentUsername);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        final User follower = userOptional.get();
        final Optional<User> userOptional1 = userRepository.findByUsername(username);
        if (userOptional1.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        final User following= userOptional1.get();
        userFollowRepository.findByFollowerAndFollowing(follower, following)
                    .ifPresent(userFollowRepository::delete);

        final int count = userFollowRepository.countByFollower(follower);
        return ProfileMapper.profileMap(following, count != 0);
    }
}
