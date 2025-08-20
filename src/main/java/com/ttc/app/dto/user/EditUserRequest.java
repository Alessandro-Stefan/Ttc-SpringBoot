package com.ttc.app.dto.user;

import jakarta.validation.constraints.NotNull;

public record EditUserRequest(
    @NotNull String username,
    @NotNull String email
) {}
