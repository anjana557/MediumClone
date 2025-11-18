package com.github.mediumclone.dto.record;


import java.util.List;

public record WriteArticleRequest(Param article) {

    public record Param(String title, String description, String body, List<String> tagList) {

    }
}
