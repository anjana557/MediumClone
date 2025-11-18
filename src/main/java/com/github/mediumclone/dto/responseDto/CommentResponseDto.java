package com.github.mediumclone.dto.responseDto;

import com.github.mediumclone.dto.response.ArticleAuthor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    Integer id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String body;
    public ArticleAuthor author;
}
