package com.ttc.app.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.task.SearchTaskCriteria;
import com.ttc.app.dto.task.SearchTaskResponse;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.entity.TaskEntity;
import com.ttc.app.mapper.TaskMapper;
import com.ttc.app.repository.TaskDefinitionRepo;
import com.ttc.app.repository.TaskRepo;
import com.ttc.app.repository.UserRepo;
import com.ttc.app.repository.Specification.TaskSpecification;
import com.ttc.app.util.constants.TaskConstants;


@Service
public class TaskServiceImpl implements TaskServiceInterface {

    private final UserRepo userRepo;

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final TaskDefinitionRepo taskDefinitionRepo;
    private final AuthenticationService authService;

    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper, TaskDefinitionRepo taskDefinitionRepo, AuthenticationService authService, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.taskDefinitionRepo = taskDefinitionRepo;
        this.authService = authService;
        this.userRepo = userRepo;
    }

    @Override
    public GetTaskResponse getTask(Long id, String token) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);
        
        //Da testare se recupero propriamente la categoria oppure ho bisogno di una chiamata al db
        if (!authService.checkUserAuthorization(token, entity.getUser().getId())) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");

        TaskDto data = taskMapper.toDto(entity);
        return new GetTaskResponse(data);
    }

    @Override
    public SearchTaskResponse searchTask(String token, SearchTaskCriteria criteria) {
        Long userId = authService.getUserIdFromToken(token);
        criteria.setUserId(userId);

        List<TaskEntity> entities = taskRepo.findAll(TaskSpecification.byCriteria(criteria));
        List<TaskDto> data = taskMapper.toDtoList(entities);
        return new SearchTaskResponse(data);
    }

    @Override
    public AddTaskResponse addTask(AddTaskRequest request, String token) {
        TaskDefinitionEntity categoryEntity = taskDefinitionRepo.getTaskDefinitionById(request.definitionId());

        if (categoryEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with id: " + request.definitionId());

        if(!authService.checkUserAuthorization(token, categoryEntity.getUser().getId())) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");

        Long userId = authService.getUserIdFromToken(token);
        TaskEntity entity = taskMapper.toEntity(request);
        entity.setUser(userRepo.getUserById(userId));

        taskRepo.save(entity);
        return new AddTaskResponse(entity.getId());
    }

    @Override
    public void editTask(Long id, EditTaskRequest request, String token) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);

        if (!authService.checkUserAuthorization(token, entity.getUser().getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");
            
        entity.setDescription(request.description());
        
        if (request.priority() != null) {
            List<TaskConstants> validPriorities = Arrays.asList(TaskConstants.PRIORITY_VERY_LOW, 
                                                                TaskConstants.PRIORITY_LOW,
                                                                TaskConstants.PRIORITY_MEDIUM,
                                                                TaskConstants.PRIORITY_HIGH,
                                                                TaskConstants.PRIORITY_VERY_HIGH);

            if (!validPriorities.contains(request.priority()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid priority value: " + request.priority());
            
            entity.setPriority(request.priority());
        }

        if (request.definitionId() != null) {
            TaskDefinitionEntity categoryEntity = taskDefinitionRepo.getTaskDefinitionById(request.definitionId());
            if (categoryEntity == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskDefinition not found with id: " + request.definitionId());

            if (!authService.checkUserAuthorization(token, categoryEntity.getUser().getId()))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");
            
            entity.setTaskDefinition(categoryEntity);
        }

        entity.setUpdatedAt(LocalDateTime.now());
        taskRepo.save(entity);
    }

    @Override
    public void deleteTask(Long id, String token) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);

        if (!authService.checkUserAuthorization(token, entity.getUser().getId())) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");
        
        taskRepo.delete(entity);
    }
}
