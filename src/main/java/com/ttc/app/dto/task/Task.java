package com.ttc.app.dto.task;

public record Task(
    Long id,
    String title,
    String description,
    Long definitionId,
    int priority
) {}
