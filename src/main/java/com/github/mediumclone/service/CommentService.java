package com.github.mediumclone.service;

import com.github.mediumclone.dto.request.CommentRequest;
import com.github.mediumclone.dto.response.CommentResponse;

public interface CommentService {

    CommentResponse createComment(String slug, CommentRequest commentRequest);
    CommentResponse getComment(String slug);
    void deleteCommentBySlugAndId(String slug, Integer id);
}
