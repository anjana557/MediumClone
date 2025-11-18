package com.github.mediumclone.mapper;

import com.github.mediumclone.dto.response.TagResponse;
import com.github.mediumclone.entity.Tag;

import java.util.List;

public class TagMapper {

    public static TagResponse map(List<Tag> tags) {
        final List<String> tagList = tags.stream().map(Tag::getName).toList();
        TagResponse tagResponse = new TagResponse();
        tagResponse.setTags(tagList);
        return tagResponse;
    }
}
