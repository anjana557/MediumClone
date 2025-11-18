package com.github.mediumclone.repository;

import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

    Optional<ArticleComment> findByArticle(Article article);
    Optional<ArticleComment> findByArticleAndId(Article article, Integer id);
}
