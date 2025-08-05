package com.ttc.app.dto.task;

public record Task(
    Long id,
    String description,
    Long definitionId,
    int priority
) {}
