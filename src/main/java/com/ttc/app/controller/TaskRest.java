package com.ttc.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.service.TaskServiceInterface;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRest {

    private final TaskServiceInterface taskService;
    public TaskRest(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponse> getTask(@PathVariable Long id) {
        GetTaskResponse response = taskService.getTask(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<AddTaskResponse> addTask(@Valid @RequestBody AddTaskRequest request) {
        AddTaskResponse response = taskService.addTask(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editTask(@PathVariable Long id, @Valid @RequestBody EditTaskRequest request) {
        taskService.editTask(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
