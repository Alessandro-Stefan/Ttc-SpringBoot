package com.ttc.app.dto.task;

import java.util.List;

public record SearchTaskResponse(
    List<TaskDto> data
) {}
