package com.ttc.app.dto.user;

public record AddUserRequest(
    String username,
    String email,
    String password
) {}
