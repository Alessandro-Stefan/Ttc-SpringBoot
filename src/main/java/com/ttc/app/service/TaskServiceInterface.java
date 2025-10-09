package com.ttc.app.service;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;

public interface TaskServiceInterface {

    GetTaskResponse getTask(Long id, String token);
    AddTaskResponse addTask(AddTaskRequest request, String token);
    void editTask(Long id, EditTaskRequest request);
    void deleteTask(Long id);
}
