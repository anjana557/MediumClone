package com.github.mediumclone.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class MultipleArticleResponse {
    public List<ArticleResponse> articleResponse;
    public Long articlesCount;
}
