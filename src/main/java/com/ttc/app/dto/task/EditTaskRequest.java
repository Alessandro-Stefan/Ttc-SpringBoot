package com.ttc.app.dto.task;

public record EditTaskRequest(
    String description,
    Integer priority,
    Long definitionId
) {}
