package com.ttc.app.dto.user;

public record UserDto(
    Long id,
    String username,
    String email,
    String password,
    String role
) {}
