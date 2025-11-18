package com.github.mediumclone.controller;

import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.entity.ArticleFavourite;
import com.github.mediumclone.service.FavouriteService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class ArticleFavouriteController {

    private final FavouriteService favouriteService;
    @PostMapping("/articles/{slug}/favorite")
    public ArticleResponse favoriteArticleBySlug(@PathVariable String slug ){
        return favouriteService.favoriteArticleBySlug(slug );
    }

    @DeleteMapping("/articles/{slug}/favorite")
    public void unFavoriteArticleBySlug(@PathVariable String slug){
        favouriteService.unFavoriteArticleBySlug(slug);
    }
}
