package com.ttc.app.dto;

public record GetTaskDefinitionResponse(
    Long id,
    String category,
    String description
) {}
