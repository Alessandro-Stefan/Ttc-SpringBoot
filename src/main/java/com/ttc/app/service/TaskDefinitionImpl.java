package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.taskDefinition.*;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.mapper.TaskDefinitionMapper;
import com.ttc.app.repository.TaskDefinitionRepo;

@Service
public class TaskDefinitionImpl implements TaskDefinitionInterface {

    private final TaskDefinitionRepo taskDefinitionRepo;
    private final TaskDefinitionMapper taskDefinitionMapper;

    public TaskDefinitionImpl(TaskDefinitionRepo taskDefinitionRepo, TaskDefinitionMapper taskDefinitionMapper) {
        this.taskDefinitionRepo = taskDefinitionRepo;
        this.taskDefinitionMapper = taskDefinitionMapper;
    }

    @Override
    public GetTaskDefinitionResponse getTaskDefinition(Long id) {
        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);
        
        TaskDefinitionDto res = taskDefinitionMapper.toDto(entity);
        return new GetTaskDefinitionResponse(res);
    }

    @Override
    public AddTaskDefinitionResponse addTaskDefinition(AddTaskDefinitionRequest request) {
        TaskDefinitionEntity entity = taskDefinitionMapper.toEntity(request);
        taskDefinitionRepo.save(entity);
        
        return new AddTaskDefinitionResponse(entity.getId());
    }

    @Override
    public void editTaskDefinition(Long id, EditTaskDefinitionRequest request) {
        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);

        entity.setCategory(request.category());
        entity.setDescription(request.description() != null ? entity.getDescription() : " ");
        taskDefinitionRepo.save(entity);
    }

    @Override
    public void deleteTaskDefinition(Long id) {
        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);

        taskDefinitionRepo.delete(entity);
    }
}
