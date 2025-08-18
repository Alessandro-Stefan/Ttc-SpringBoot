package com.ttc.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttc.app.dto.user.GetUserResponse;
import com.ttc.app.service.UserServiceInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/users")
public class UserRest {

    private final UserServiceInterface userService;
    public UserRest(UserServiceInterface userService) {
        this.userService = userService;
    }

    //Da correggere la response
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@RequestParam Long id) {
        GetUserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
}
