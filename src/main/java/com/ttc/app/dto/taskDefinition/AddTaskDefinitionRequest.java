package com.ttc.app.dto.taskDefinition;

import jakarta.validation.constraints.NotNull;

public record AddTaskDefinitionRequest(
    
    @NotNull
    String category,
    
    String description) {
}