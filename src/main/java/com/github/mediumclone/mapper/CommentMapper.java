package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.ArticleAuthor;
import com.github.mediumclone.dto.response.CommentResponse;
import com.github.mediumclone.dto.responseDto.CommentResponseDto;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleComment;
import com.github.mediumclone.entity.User;

public class CommentMapper {
    public static CommentResponse commentMap(ArticleComment articleComment){
        CommentResponse commentResponse = new CommentResponse();
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        ArticleAuthor articleAuthor = new ArticleAuthor();

        commentResponseDto.setId(articleComment.getId());
        commentResponseDto.setBody(articleComment.getContent());
        commentResponseDto.setCreatedAt(articleComment.getCreatedAt());

        final Article article = articleComment.getArticle();
        commentResponseDto.setUpdatedAt(article.getUpdatedAt());
        final User author = articleComment.getAuthor();
        articleAuthor.setUsername(author.getUsername());
        articleAuthor.setBio(author.getBio());
        articleAuthor.setBio(author.getImageUrl());
        articleAuthor.setFollowing(true);
        commentResponseDto.setAuthor(articleAuthor);
        commentResponse.setComment(commentResponseDto);
        return commentResponse;

    }
}
