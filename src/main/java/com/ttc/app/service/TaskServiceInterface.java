package com.ttc.app.service;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.task.SearchTaskCriteria;
import com.ttc.app.dto.task.SearchTaskResponse;

public interface TaskServiceInterface {

    GetTaskResponse getTask(Long id, String token);
    SearchTaskResponse searchTask(String token, SearchTaskCriteria sCriteria);
    AddTaskResponse addTask(AddTaskRequest request, String token);
    void editTask(Long id, EditTaskRequest request, String token);
    void deleteTask(Long id, String token);
}
