package com.ttc.app.dto.user;

public record LoginRequest(
    String username,
    String password
) {}
