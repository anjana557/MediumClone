package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.ArticleAuthor;
import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.dto.responseDto.ArticleResponseDto;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleTag;
import com.github.mediumclone.entity.Tag;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.service.impl.ArticleServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;

public class ArticleMapper {


    public static ArticleResponse articleMap(Article saveArticle){
        ArticleResponse articleResponse = new ArticleResponse();
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        ArticleAuthor articleAuthor = new ArticleAuthor();

        final User author = saveArticle.getAuthor();
        articleAuthor.setUsername(author.getUsername());
        articleAuthor.setBio(author.getBio());
        articleAuthor.setImage(author.getImageUrl());
        articleAuthor.setFollowing(false);

        articleResponseDto.setSlug(saveArticle.getSlug());
        articleResponseDto.setTitle(saveArticle.getTitle());
        articleResponseDto.setDescription(saveArticle.getDescription());

//        Set<ArticleTag> articleTags = saveArticle.getArticleTags();
//        if (articleTags != null) {
//            String[] tagList = articleTags.stream()
//                    .map(articleTag -> articleTag.getTag().getName())
//                    .toArray(String[]::new);
//            articleResponseDto.setTagList(tagList);
//        }

        final Set<String> collect = saveArticle.getArticleTags().stream().map(ArticleTag::getTag).map(Tag::getName).collect(Collectors.toSet());
        articleResponseDto.setTagList(collect.toArray(new String[0]));

        articleResponseDto.setCreatedAt(saveArticle.getCreatedAt());
        articleResponseDto.setUpdatedAt(saveArticle.getUpdatedAt());
        articleResponseDto.setBody(saveArticle.getContent());

        articleResponse.setArticle(articleResponseDto);
        return articleResponse;

    }
}
