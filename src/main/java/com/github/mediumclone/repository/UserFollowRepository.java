package com.github.mediumclone.repository;

import com.github.mediumclone.entity.User;
import com.github.mediumclone.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFollowRepository extends JpaRepository<UserFollow, Integer> {
    boolean existsByFollowerAndFollowing(User follower, User following);

    Optional<UserFollow> findByFollowerAndFollowing(User follower, User following);

    //Optional<UserFollow> findByUsername(User username);
    int countByFollower(User user);
}
