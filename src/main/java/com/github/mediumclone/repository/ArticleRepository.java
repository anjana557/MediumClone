package com.github.mediumclone.repository;

import com.github.mediumclone.dto.request.ArticleUpdate;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    Optional<Article> findBySlug(String slug);

    Page<Article> findAll(Specification<Article> spec, Pageable pageable);
}

