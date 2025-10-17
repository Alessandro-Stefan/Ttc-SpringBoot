package com.ttc.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.AddTaskResponse;
import com.ttc.app.dto.task.EditTaskRequest;
import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.task.SearchTaskCriteria;
import com.ttc.app.dto.task.SearchTaskResponse;
import com.ttc.app.service.TaskServiceInterface;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRest {

    private final TaskServiceInterface taskService;
    public TaskRest(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponse> getTask(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        GetTaskResponse response = taskService.getTask(id, token);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<SearchTaskResponse> searchTasks(@RequestHeader("Authorization") String token, SearchTaskCriteria sCriteria) {
        SearchTaskResponse response = taskService.searchTask(token, sCriteria);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<AddTaskResponse> addTask(@RequestHeader("Authorization") String token, @Valid @RequestBody AddTaskRequest request) {
        AddTaskResponse response = taskService.addTask(request, token);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editTask(@RequestHeader("Authorization") String token, @PathVariable Long id,  @RequestBody EditTaskRequest request) {
        taskService.editTask(id, request, token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        taskService.deleteTask(id, token);
        return ResponseEntity.noContent().build();
    }
}
