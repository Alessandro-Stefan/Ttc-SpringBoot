package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.user.*;
import com.ttc.app.entity.UserEntity;
import com.ttc.app.mapper.UserMapper;
import com.ttc.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {

    UserRepo userRepo;
    UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public GetUserResponse getUserById(Long id) {
        UserEntity userEntity = userRepo.getUserById(id);
        if (userEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);

        UserDto userDto = userMapper.toDto(userEntity);
        GetUserResponse response = new GetUserResponse(userDto);
        return response;
    }

    @Override
    public AddUserResponse addUser(AddUserRequest request) {
        UserEntity userEntity = userMapper.toEntity(request);
        userRepo.save(userEntity);
        return new AddUserResponse(userEntity.getId());
    }

    @Override
    public void editUser (Long id, EditUserRequest request) {
        UserEntity userEntity = userRepo.getUserById(id);
        if (userEntity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);

        userEntity.setUsername(request.username());
        userEntity.setEmail(request.email());
        userRepo.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepo.getUserById(id);
        if (userEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);

        userRepo.delete(userEntity);
    }   
}
