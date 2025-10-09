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
@Table(name = "tag")
public class Tag {

    @Id
    @Column(length = 20, nullable = false)
    private String name;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    public Tag(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name is null or blank.");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
