package com.ttc.app.service;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.AddTaskDefinitionResponse;
import com.ttc.app.dto.taskDefinition.EditTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.GetTaskDefinitionResponse;

public interface TaskDefinitionServiceInterface {
    GetTaskDefinitionResponse getTaskDefinition(Long id, String token);
    AddTaskDefinitionResponse addTaskDefinition(AddTaskDefinitionRequest request, String token);
    void editTaskDefinition(Long id, EditTaskDefinitionRequest request);
    void deleteTaskDefinition(Long id);
}
