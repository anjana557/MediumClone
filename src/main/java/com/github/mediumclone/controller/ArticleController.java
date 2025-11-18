package com.github.mediumclone.controller;

import com.github.mediumclone.dto.request.ArticleRequest;
import com.github.mediumclone.dto.request.ArticleUpdate;
import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.dto.response.MultipleArticleResponse;
import com.github.mediumclone.dto.responseDto.ArticleResponseDto;
import com.github.mediumclone.service.ArticleService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/articles")
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.createArticle(articleRequest);
    }


    @GetMapping("/articles/{slug}")
    public ArticleResponse getArticleBySlug(@PathVariable String slug){
        return  articleService.getArticleBySlug(slug);
    }


    @PutMapping("/articles/{slug}")
    private ArticleResponse updateArticleBySlug(@PathVariable String slug, @RequestBody ArticleUpdate articleUpdate){
        return articleService.updateArticleBySlug(slug,articleUpdate) ;

    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/articles/{slug}")
    public void deleteArticleBySlug(@PathVariable String slug){
        articleService.deleteArticleBySlug(slug);
    }

    @GetMapping("/articles/feed")
    public MultipleArticleResponse pageResult(@RequestParam Integer offset, @RequestParam Integer limit){
        return articleService.pageResult(offset, limit);
    }

    public MultipleArticleResponse getRecentPageResult(@RequestParam String tag,
                                                       @RequestParam String author,
                                                       @RequestParam String favourite,
                                                       @RequestParam Integer offset,
                                                       @RequestParam Integer limit){
        return articleService.getRecentPageResult(tag, author, favourite, offset, limit);
    }
}
