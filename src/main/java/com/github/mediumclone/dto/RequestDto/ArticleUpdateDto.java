package com.github.mediumclone.dto.RequestDto;

import lombok.Data;

@Data
public class ArticleUpdateDto {
    String title;
    String description;
    String body;
}
