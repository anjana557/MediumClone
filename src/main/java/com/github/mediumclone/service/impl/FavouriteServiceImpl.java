package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleFavourite;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.exception.ArticleNotFoundException;
import com.github.mediumclone.exception.UserNotFoundException;
import com.github.mediumclone.mapper.ArticleFavoriteMapper;
import com.github.mediumclone.mapper.ArticleMapper;
import com.github.mediumclone.repository.ArticleFavouriteRepository;
import com.github.mediumclone.repository.ArticleRepository;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final ArticleRepository articleRepository;
    private final ArticleFavouriteRepository articleFavouriteRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ArticleResponse favoriteArticleBySlug(String slug) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("article not found");
        }
        final Article article = bySlug.get();

        final Optional<User> userOptional = userRepository.findByUsername("anjana");
        if (userOptional.isEmpty()){
            throw  new UserNotFoundException("User not found");
        }
        final User user = userOptional.get();
        ArticleFavourite articleFavourite = new ArticleFavourite(user, article);
        final ArticleFavourite save = articleFavouriteRepository.save(articleFavourite);

        return ArticleFavoriteMapper.favoriteMap(save);
    }

    @Override
    public void unFavoriteArticleBySlug(String slug) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("article not found");
        }
        final Article article = bySlug.get();

        final Optional<User> userOptional = userRepository.findByUsername("anjana");
        if (userOptional.isEmpty()){
            throw  new UserNotFoundException("User not found");
        }
        final User user = userOptional.get();
        ArticleFavourite articleFavourite = new ArticleFavourite(user, article);
        articleFavouriteRepository.findByArticleAndUser(article, user)
                .ifPresent(articleFavouriteRepository::delete);

    }
}
