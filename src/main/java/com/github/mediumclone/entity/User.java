package com.github.mediumclone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false, unique = true  )
    private String username;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 500)
    private String bio;

    public User(String email, String username, String password) {
        if(!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("email must not be null or blank.");
        }

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be null or blank.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password must not be null or blank.");
        }

        this.email = email;
        this.username = username;
        this.password = password;
    }
    public boolean equalsEmail(String email){
        return this.email.equals(email);
    }
    public boolean equalsUsername(String username){
        return this.username.equals(username);
    }
    public void setEmail(String email){
        if (email == null || email.isBlank()){
            log.info("not set because the email is blank");
            return;
        }
        this.email = email;

    }
    public void setUsername(String username){
        if (username == null || username.isBlank()){
            log.info("not set because the username is empty or equals. username={}", username);
            return;
        }
        this.username =username;
    }
    public void setBio(String bio){
        if (bio ==null || bio.isBlank()){
            log.info("not set because bio is blank.");
            return;
        }
        this.bio = bio;
    }
    public void setImageUrl(String imageUrl){
        if (imageUrl == null || imageUrl.isBlank()){
            log.info("not set because the imageUrl is blank.");
            return;
        }
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
