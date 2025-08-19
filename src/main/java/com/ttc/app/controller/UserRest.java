package com.ttc.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ttc.app.dto.user.*;
import com.ttc.app.service.UserServiceInterface;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/users")
public class UserRest {

    private final UserServiceInterface userService;
    public UserRest(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long id) {
        GetUserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<AddUserResponse> addUser(@Valid @RequestBody AddUserRequest request) {
        AddUserResponse response = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
