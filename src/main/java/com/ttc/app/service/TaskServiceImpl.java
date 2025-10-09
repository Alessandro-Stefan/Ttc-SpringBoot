package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.entity.TaskEntity;
import com.ttc.app.mapper.TaskMapper;
import com.ttc.app.repository.TaskDefinitionRepo;
import com.ttc.app.repository.TaskRepo;

@Service
public class TaskServiceImpl implements TaskServiceInterface {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final TaskDefinitionRepo taskDefinitionRepo;
    private final AuthenticationService authService;

    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper, TaskDefinitionRepo taskDefinitionRepo, AuthenticationService authService) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.taskDefinitionRepo = taskDefinitionRepo;
        this.authService = authService;
    }

    @Override
    public GetTaskResponse getTask(Long id, String token) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);
        
        //Da testare se recupero propriamente la categoria oppure ho bisogno di una chiamata al db
        TaskDefinitionEntity categoryEntity = entity.getTaskDefinition();
        if (!authService.checkUserAuthorization(token, categoryEntity.getUser().getId())) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");

        TaskDto data = taskMapper.toDto(entity);
        return new GetTaskResponse(data);
    }

    @Override
    public AddTaskResponse addTask(AddTaskRequest request, String token) {
        TaskDefinitionEntity categoryEntity = taskDefinitionRepo.getTaskDefinitionById(request.definitionId());

        if (categoryEntity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TaskDefinition not found with id: " + request.definitionId());

        if(!authService.checkUserAuthorization(token, categoryEntity.getUser().getId())) 
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserId and resourceOwnerId mismatch");

        TaskEntity entity = taskMapper.toEntity(request);
        taskRepo.save(entity);
        return new AddTaskResponse(entity.getId());
    }

    @Override
    public void editTask(Long id, EditTaskRequest request) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);

        entity.setDescription(request.description() == null ? " " : request.description());
        //TODO: Da inserire una classe che contiene le constanti di varie cose, tra cui la defaultPriority
        entity.setPriority(request.priority() == null ? 0 : request.priority());
        taskRepo.save(entity);
    }

    @Override
    public void deleteTask(Long id) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);
        
        taskRepo.delete(entity);
    }
}
