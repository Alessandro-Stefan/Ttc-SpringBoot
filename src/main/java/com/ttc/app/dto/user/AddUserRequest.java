package com.ttc.app.dto.user;

import jakarta.validation.constraints.NotNull;

public record AddUserRequest(
    @NotNull String username,
    @NotNull String email,
    @NotNull String password
) {}
