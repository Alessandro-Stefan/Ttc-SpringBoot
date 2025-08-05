package com.ttc.app.dto.task;

import jakarta.validation.constraints.NotNull;

public record EditTaskRequest(
    String description,
    @NotNull
    int priority
) {}
