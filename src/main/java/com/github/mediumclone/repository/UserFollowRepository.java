package com.github.mediumclone.repository;

import com.github.mediumclone.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Integer> {
}
