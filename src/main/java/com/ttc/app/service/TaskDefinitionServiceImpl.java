package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.taskDefinition.*;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.entity.UserEntity;
import com.ttc.app.mapper.TaskDefinitionMapper;
import com.ttc.app.repository.TaskDefinitionRepo;
import com.ttc.app.repository.UserRepo;

@Service
public class TaskDefinitionServiceImpl implements TaskDefinitionServiceInterface {

    private final TaskDefinitionRepo taskDefinitionRepo;
    private final TaskDefinitionMapper taskDefinitionMapper;
    private final UserRepo userRepo;

    public TaskDefinitionServiceImpl(TaskDefinitionRepo taskDefinitionRepo, TaskDefinitionMapper taskDefinitionMapper, UserRepo userRepo) {
        this.taskDefinitionRepo = taskDefinitionRepo;
        this.taskDefinitionMapper = taskDefinitionMapper;
        this.userRepo = userRepo;
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
        if (userRepo.getUserById(request.userId()) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + request.userId());

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
        if (request.description() != null && !request.description().isBlank()) 
            entity.setDescription(request.description());
                        
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
