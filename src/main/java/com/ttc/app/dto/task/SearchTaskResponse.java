package com.ttc.app.dto.task;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record SearchTaskResponse(
    @NotNull
    List<Task> data
) {}
