package com.github.mediumclone.entity;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(length = 50, unique = true, nullable = false)
    private String slug;

    @Column(length = 50, unique = true, nullable = false)
    private String title;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @Column(length = 1_000, nullable = false)
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<ArticleTag> articleTags = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();


    private static String titleToSlug(String title){
        return title.toLowerCase().replaceAll("\\s+","-");
    }

    public Article(User author, String title, String description, String content){
        if (author == null || author.getId()== null){
            throw new IllegalArgumentException("author is null or unknown user.");
        }
        if (!StringUtils.hasText(title)){
            throw  new IllegalArgumentException("title is blank or null.");
        }
        if (description== null || description.isBlank()){
            throw  new IllegalArgumentException("description is null or blank.");
        }
        if (content == null || content.isBlank()){
            throw new IllegalArgumentException("content is null or blank.");
        }
        this.author = author;
        this.slug = Article.titleToSlug(title);
        this.title = title;
        this.description = description;
        this.content = content;
    }
    private  boolean isNotAuthor(User author){
        return  !this.author.equals(author);
    }

    public  void setTitle(String title){
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("title is null or blank.");
        }
        this.title = title;
        this.slug = Article.titleToSlug(this.title);
        this.updatedAt = LocalDateTime.now();
    }
    public void setDescription(String description){
        if (description == null || description.isBlank()){
            throw  new IllegalArgumentException("description is null or blank.");
        }
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    public void  setContent(String content){
        if (content == null || content.isBlank()){
            throw  new IllegalArgumentException("content is null or blank.");
        }
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
    public void addTag(ArticleTag tag){
        articleTags.add(tag);
        tag.setArticle(this);

    }


}
