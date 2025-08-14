package com.ttc.app.dto.task;

public record TaskDto(
    Long id,
    String title,
    String description,
    Long definitionId,
    int priority
) {}
