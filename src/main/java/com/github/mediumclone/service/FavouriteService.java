package com.github.mediumclone.service;

import com.github.mediumclone.dto.response.ArticleResponse;

public interface FavouriteService {
    ArticleResponse favoriteArticleBySlug(String slug);
    void unFavoriteArticleBySlug(String slug);
}
