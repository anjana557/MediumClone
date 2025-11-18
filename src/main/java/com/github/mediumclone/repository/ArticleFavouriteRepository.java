package com.github.mediumclone.repository;

import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleFavourite;
import com.github.mediumclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleFavouriteRepository extends JpaRepository<ArticleFavourite, Integer> {
    Optional<ArticleFavourite> findByArticleAndUser(Article article, User user);
}
