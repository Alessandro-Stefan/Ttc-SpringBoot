package com.ttc.app.service;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.GetTaskResponse;

public interface TaskServiceInterface {

    GetTaskResponse getTask(Long id);
    AddTaskResponse addTask(AddTaskRequest request);
}
