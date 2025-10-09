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
        name = "article_favourite",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "article_id"})}
)
public class ArticleFavourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public ArticleFavourite(User user, Article article){
        if (user == null || user.getId()== null){
            throw new IllegalArgumentException("user is null or unknown user.");
        }
        if (article == null || article.getId()== null){
            throw new IllegalArgumentException("article is null or unknown article.");
        }
        this.user = user;
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArticleFavourite that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, article);
    }
}
