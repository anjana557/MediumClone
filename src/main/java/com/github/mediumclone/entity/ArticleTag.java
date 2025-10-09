package com.github.mediumclone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "article_tag",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"article_id", "tag_name"})}
)
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "created_at", updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "tag_name")
    private Tag tag;

    public ArticleTag(Article article, Tag tag){
        if (article == null || article.getId()== null){
            throw new IllegalArgumentException("article is null or unknown article.");
        }
        if (tag == null || tag.getName() == null){
            throw new IllegalArgumentException("tag is null or unknown tag.");
        }
        this.article = article;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArticleTag that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(article, that.article) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, tag);
    }
}
