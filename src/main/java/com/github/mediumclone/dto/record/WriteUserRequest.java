package com.github.mediumclone.dto.record;

import com.github.mediumclone.dto.request.Param;

public record WriteUserRequest(Param user) {
    public record Param(String username, String email, String password){

    }
}
