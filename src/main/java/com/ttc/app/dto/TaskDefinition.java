package com.ttc.app.dto;

import jakarta.validation.constraints.NotNull;

public record TaskDefinition
(
    @NotNull
    Long id,
    @NotNull
    String category,
    String description
) {}
