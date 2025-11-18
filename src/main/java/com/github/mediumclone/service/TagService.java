package com.github.mediumclone.service;

import com.github.mediumclone.dto.response.TagResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    TagResponse getTags();
}
