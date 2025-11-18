package com.github.mediumclone.dto.responseDto;

import com.github.mediumclone.dto.response.ArticleAuthor;
import com.github.mediumclone.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ArticleResponseDto{
        String slug;
        String title;
        String description;
        String body;
        String[] tagList;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        boolean favourite;
        int favouriteCount;
        public ArticleAuthor author;
}
