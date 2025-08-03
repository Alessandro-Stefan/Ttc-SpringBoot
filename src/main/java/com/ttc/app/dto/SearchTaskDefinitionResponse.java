package com.ttc.app.dto;

import java.util.List;

public record SearchTaskDefinitionResponse(
    List<TaskDefinition> data
) {}
