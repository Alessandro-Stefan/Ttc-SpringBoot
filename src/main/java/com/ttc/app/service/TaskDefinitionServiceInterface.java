package com.ttc.app.service;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.AddTaskDefinitionResponse;
import com.ttc.app.dto.taskDefinition.EditTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.GetTaskDefinitionResponse;
import com.ttc.app.dto.taskDefinition.SearchTaskDefinitionCriteria;
import com.ttc.app.dto.taskDefinition.SearchTaskDefinitionResponse;

public interface TaskDefinitionServiceInterface {
    GetTaskDefinitionResponse getTaskDefinition(Long id, String token);
    SearchTaskDefinitionResponse searchTaskDefinition(String token, SearchTaskDefinitionCriteria criteria);
    AddTaskDefinitionResponse addTaskDefinition(AddTaskDefinitionRequest request, String token);
    AddTaskDefinitionResponse addDefaultTaskDefinition(AddTaskDefinitionRequest request, String token);
    void editTaskDefinition(Long id, EditTaskDefinitionRequest request, String token);
    void deleteTaskDefinition(Long id, String token);
}
