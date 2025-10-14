package com.ttc.app.dto.taskDefinition;

import jakarta.validation.constraints.NotNull;

public record TaskDefinitionDto
(
    @NotNull
    Long id,
    @NotNull
    String category,
    String description,
    Long userId
) {}
