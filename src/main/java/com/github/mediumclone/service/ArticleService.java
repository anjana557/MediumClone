package com.github.mediumclone.service;

import com.github.mediumclone.dto.request.ArticleRequest;
import com.github.mediumclone.dto.request.ArticleUpdate;
import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.dto.response.MultipleArticleResponse;
import org.springframework.stereotype.Service;


public interface ArticleService {
    ArticleResponse createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleBySlug(String slug);
    ArticleResponse updateArticleBySlug(String slug, ArticleUpdate articleUpdate);
    void deleteArticleBySlug(String slug);
    MultipleArticleResponse pageResult(Integer offset, Integer limit);
    MultipleArticleResponse  getRecentPageResult(String tag, String author,
                                                 String favorite, Integer offset,
                                                 Integer limit);
}
