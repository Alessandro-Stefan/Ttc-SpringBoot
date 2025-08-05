package com.ttc.app.dto.taskDefinition;

public record GetTaskDefinitionResponse(
    Long id,
    String category,
    String description
) {}
