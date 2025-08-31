package com.ttc.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttc.app.dto.task.GetTaskResponse;
import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.AddTaskDefinitionResponse;
import com.ttc.app.dto.taskDefinition.EditTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.GetTaskDefinitionResponse;
import com.ttc.app.service.TaskDefinitionInterface;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/tasks/category")
public class TaskDefinitionRest {

    private final TaskDefinitionInterface taskDefinitionService;

    public TaskDefinitionRest(TaskDefinitionInterface taskDefinitionService) {
        this.taskDefinitionService = taskDefinitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskDefinitionResponse> getTaskDefinition(@PathVariable Long id) {
        GetTaskDefinitionResponse response = taskDefinitionService.getTaskDefinition(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AddTaskDefinitionResponse> addTaskDefinition(@RequestBody @Valid AddTaskDefinitionRequest request) {
        AddTaskDefinitionResponse response = taskDefinitionService.addTaskDefinition(request);
        return ResponseEntity.ok(response);
    }

    //TODO: [BUG] Se non metti un parametro nullable diventa null invece che non cambiare da come era prima.
    @PutMapping("/{id}")
    public ResponseEntity<Void> editTaskDefinition(@PathVariable Long id, @RequestBody @Valid EditTaskDefinitionRequest request) {
        taskDefinitionService.editTaskDefinition(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskDefinition(@PathVariable Long id) {
        taskDefinitionService.deleteTaskDefinition(id);
        return ResponseEntity.noContent().build();
    }
}