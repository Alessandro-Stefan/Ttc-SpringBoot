package com.ttc.app.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.user.*;
import com.ttc.app.entity.UserEntity;
import com.ttc.app.mapper.UserMapper;
import com.ttc.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.encoder = encoder;
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
        //TODO: Da fixare l'utilizzo della doppia richiesta
        String encodedPsw = encoder.encode(request.password());
        AddUserRequest req = new AddUserRequest(request.username(), encodedPsw, request.email());

        UserEntity userEntity = userMapper.toEntity(req);

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
