package com.github.mediumclone.dto.RequestDto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {
     String title;
     String description;
     String body;
    List<String> tagList = new ArrayList<>();
}
