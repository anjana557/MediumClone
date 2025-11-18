package com.github.mediumclone.controller;

import com.github.mediumclone.dto.response.TagResponse;
import com.github.mediumclone.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping("/tags")
    public TagResponse getAllTags(){
        return  tagService.getTags();
    }
}
