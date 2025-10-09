package com.github.mediumclone.repository;

import com.github.mediumclone.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {
}
