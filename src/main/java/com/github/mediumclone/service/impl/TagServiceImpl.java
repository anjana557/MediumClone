package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.response.TagResponse;
import com.github.mediumclone.entity.Tag;
import com.github.mediumclone.mapper.TagMapper;
import com.github.mediumclone.repository.TagRepository;
import com.github.mediumclone.service.TagService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    @Override
    public TagResponse getTags() {
        final List<Tag> tags = tagRepository.findAll();
        return TagMapper.map(tags);
    }
}
