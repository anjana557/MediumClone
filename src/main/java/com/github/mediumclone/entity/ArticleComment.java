package com.github.mediumclone.entity;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.InvalidIsolationLevelException;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article_comment")
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    private Article article;

    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public ArticleComment(Article article, User author, String content){
        if (article == null || article.getId()== null){
            throw new IllegalArgumentException("article is null or not saved article.");
        }
        if (author == null || author.getId() == null){
            throw new IllegalArgumentException("author is null or unknown user.");
        }
        if (content == null || content.isBlank()){
            throw new IllegalArgumentException("content must not be null or blank.");
        }
        this.article = article;
        this.author = author;
        this.content = content;
    }
    public boolean isNotAuthor(User user){
        return !this.author.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArticleComment that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
