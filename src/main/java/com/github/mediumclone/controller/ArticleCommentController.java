package com.github.mediumclone.controller;

import com.github.mediumclone.dto.request.CommentRequest;
import com.github.mediumclone.dto.response.CommentResponse;
import com.github.mediumclone.service.CommentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class ArticleCommentController {

    private final CommentService commentService;
    @PostMapping("/articles/{slug}/comments")
    public CommentResponse createComment(@PathVariable String slug, @RequestBody CommentRequest commentRequest){
        return commentService.createComment(slug, commentRequest);
    }

    @GetMapping("/articles/{slug}/comments")
    public CommentResponse getComment(@PathVariable String slug){
        return  commentService.getComment(slug);
    }

    @GetMapping("/article/{slug}/comments/{id}")
    public void deleteCommentBySlugAndId(@PathVariable String slug, @PathVariable Integer id){
        commentService.deleteCommentBySlugAndId(slug, id);
    }
}
