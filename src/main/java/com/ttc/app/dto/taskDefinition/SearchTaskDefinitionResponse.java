package com.ttc.app.dto.taskDefinition;

import java.util.List;

public record SearchTaskDefinitionResponse(
    List<TaskDefinition> data
) {}
