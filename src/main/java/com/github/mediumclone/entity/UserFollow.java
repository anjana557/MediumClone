package com.github.mediumclone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user_follow",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"follower_id", "following_id"})}
)
public class UserFollow {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    public UserFollow(User follower , User following){
     if (follower == null || follower.getId() == null){
      throw new IllegalArgumentException("follower is null or unknown user.");
     }
     if (following == null || following.getId() == null){
      throw new IllegalArgumentException("following is null or unknown user.");
     }
     this.follower = follower;
     this.following = following;
    }

 @Override
 public boolean equals(Object o) {
  if (!(o instanceof UserFollow that)) return false;
     return Objects.equals(id, that.id);
 }

 @Override
 public int hashCode() {
  return Objects.hashCode(id);
 }
}
