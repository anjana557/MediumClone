package com.github.mediumclone.dto.response;

import lombok.Data;

@Data
public class ArticleAuthor {
    String username;
    String bio;
    String image;
    boolean following;
}
