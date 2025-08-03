package com.ttc.app.dto;

import jakarta.validation.constraints.NotNull;

public record EditTaskDefinitionRequest(
    @NotNull
    String category,
    String description
) {}
