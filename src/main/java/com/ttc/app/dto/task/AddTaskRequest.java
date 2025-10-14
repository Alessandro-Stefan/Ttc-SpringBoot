package com.ttc.app.dto.task;

import jakarta.validation.constraints.NotNull;

public record AddTaskRequest(
    @NotNull
    String title,
    String description,
    @NotNull
    Long definitionId,
    @NotNull
    int priority
) {}
