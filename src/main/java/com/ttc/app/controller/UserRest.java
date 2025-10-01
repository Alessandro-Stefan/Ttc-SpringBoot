package com.ttc.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ttc.app.dto.user.*;
import com.ttc.app.service.AuthenticationServiceImpl;
import com.ttc.app.service.UserServiceInterface;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/users")
public class UserRest {

    private final UserServiceInterface userService;
    private final AuthenticationServiceImpl authService;

    public UserRest(UserServiceInterface userService, AuthenticationManager authenticationManager, AuthenticationServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long id) {
        GetUserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    //TODO: Da modificare in registrazione utente
    // @PostMapping
    // public ResponseEntity<AddUserResponse> addUser(@Valid @RequestBody AddUserRequest request) {
    //     AddUserResponse response = userService.addUser(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // }

    @PutMapping("/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @Valid @RequestBody EditUserRequest request) {
        userService.editUser(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
