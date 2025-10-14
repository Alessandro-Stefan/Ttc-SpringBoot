package com.ttc.app.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.taskDefinition.*;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.mapper.TaskDefinitionMapper;
import com.ttc.app.repository.TaskDefinitionRepo;
import com.ttc.app.repository.UserRepo;

@Service
public class TaskDefinitionServiceImpl implements TaskDefinitionServiceInterface {

    private final TaskDefinitionRepo taskDefinitionRepo;
    private final TaskDefinitionMapper taskDefinitionMapper;
    private final UserRepo userRepo;
    private final AuthenticationService authService;

    public TaskDefinitionServiceImpl(TaskDefinitionRepo taskDefinitionRepo, TaskDefinitionMapper taskDefinitionMapper, UserRepo userRepo, AuthenticationService authService) {
        this.taskDefinitionRepo = taskDefinitionRepo;   
        this.taskDefinitionMapper = taskDefinitionMapper;
        this.userRepo = userRepo;
        this.authService = authService;
    }

    @Override
    public GetTaskDefinitionResponse getTaskDefinition(Long id, String token) {

        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);

        if (!authService.checkUserAuthorization(token, entity.getUser().getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch.");

        TaskDefinitionDto res = taskDefinitionMapper.toDto(entity);
        return new GetTaskDefinitionResponse(res);
    }

    @Override
    public AddTaskDefinitionResponse addTaskDefinition(AddTaskDefinitionRequest request, String token) {
        Long userId = authService.getUserIdFromToken(token); 
        
        TaskDefinitionEntity entity = taskDefinitionMapper.toEntity(request);
        entity.setUser(userRepo.getUserById(userId));
        taskDefinitionRepo.save(entity);
        
        return new AddTaskDefinitionResponse(entity.getId());
    }

    @Override
    public AddTaskDefinitionResponse addDefaultTaskDefinition(AddTaskDefinitionRequest request, String token) {
        Long userId = authService.getUserIdFromToken(token);

        if (!authService.checkAdminAuthorization(token)) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Role unauthorized.");
        
        TaskDefinitionEntity entity = taskDefinitionMapper.toEntity(request);
        entity.setUser(userRepo.getUserById(userId));
        taskDefinitionRepo.save(entity);
        
        return new AddTaskDefinitionResponse(entity.getId());
    }

    @Override
    public void editTaskDefinition(Long id, EditTaskDefinitionRequest request, String token) {
        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);

        if (!authService.checkUserAuthorization(token, entity.getUser().getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch.");

        if (request.category() != null && !request.category().isEmpty())
            entity.setCategory(request.category());

        entity.setDescription(request.description());
        entity.setUpdatedAt(LocalDateTime.now());

        taskDefinitionRepo.save(entity);
    }

    @Override
    public void deleteTaskDefinition(Long id, String token) {
        TaskDefinitionEntity entity = taskDefinitionRepo.getTaskDefinitionById(id);
        if (entity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with ID: " + id);

        if (!authService.checkUserAuthorization(token, entity.getUser().getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch.");

        taskDefinitionRepo.delete(entity);
    }
}
