package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.ArticleAuthor;
import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.dto.responseDto.ArticleResponseDto;
import com.github.mediumclone.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public class ArticleFavoriteMapper {

    public static ArticleResponse favoriteMap(ArticleFavourite saveArticle){

        ArticleResponse articleResponse = new ArticleResponse();
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        ArticleAuthor articleAuthor = new ArticleAuthor();
        final Article article = saveArticle.getArticle();
        final User user = saveArticle.getUser();
        //final User author = article.getAuthor();
        articleResponseDto.setSlug(article.getSlug());
        articleResponseDto.setTitle(article.getTitle());
        articleResponseDto.setBody(article.getContent());
        articleResponseDto.setDescription(article.getDescription());
        articleResponseDto.setFavourite(true);
        articleResponseDto.setCreatedAt(article.getCreatedAt());
        articleResponseDto.setUpdatedAt(article.getUpdatedAt());
        articleAuthor.setUsername(user.getUsername());
        articleAuthor.setImage(user.getImageUrl());
        articleAuthor.setBio(user.getBio());
        articleAuthor.setFollowing(true);
        return articleResponse;
    }
}
