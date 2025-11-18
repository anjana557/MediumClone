package com.github.mediumclone.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TagResponse {
    List<String> tags = new ArrayList<>();
}
