package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskEntity;
import com.ttc.app.mapper.TaskMapper;
import com.ttc.app.repository.TaskDefinitionRepo;
import com.ttc.app.repository.TaskRepo;

@Service
public class TaskServiceImpl implements TaskServiceInterface {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final TaskDefinitionRepo taskDefinitionRepo;

    public TaskServiceImpl(TaskRepo taskRepo, TaskMapper taskMapper, TaskDefinitionRepo taskDefinitionRepo) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.taskDefinitionRepo = taskDefinitionRepo;
    }

    @Override
    public GetTaskResponse getTask(Long id) {
        TaskEntity entity = taskRepo.getTaskById(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id);
        
        TaskDto data = taskMapper.toDto(entity);
        return new GetTaskResponse(data);
    }

    @Override
    public AddTaskResponse addTask(AddTaskRequest request) {
        if (taskDefinitionRepo.getTaskDefinitionById(request.definitionId()) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TaskDefinition not found with id: " + request.definitionId());
        
        TaskEntity entity = taskMapper.toEntity(request);
        taskRepo.save(entity);
        return new AddTaskResponse(entity.getId());
    }
}
