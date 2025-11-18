package com.github.mediumclone.dto.request;

import lombok.Data;

import java.util.List;

public record Param(String title, String description, String body, List<String> tagList) {

    }