package com.ttc.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ttc.app.dto.user.*;
import com.ttc.app.entity.UserEntity;
import com.ttc.app.mapper.UserMapper;
import com.ttc.app.repository.UserRepo;
import com.ttc.app.repository.Specification.UserSpecification;

@Service
public class UserServiceImpl implements UserServiceInterface, UserDetailsService {

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
    public SearchUserResponse searchUsers(String token, SearchUserCriteria criteria) {
        List<UserEntity> entities = criteria != null ?
                                                    userRepo.findAll(UserSpecification.byCriteria(criteria)) :
                                                    userRepo.findAll();
        List<UserDto> data = userMapper.toDtos(entities);

        return new SearchUserResponse(data);
    }

    @Override
    public AddUserResponse addUser(AddUserRequest request) {;

        UserEntity entity = new UserEntity();
        entity.setUsername(request.username());
        entity.setPassword(encoder.encode(request.password()));
        entity.setEmail(request.email());
        entity.setCreatedAt(LocalDateTime.now());

        userRepo.save(entity);
        return new AddUserResponse(entity.getId());
    }

    @Override
    public void editUser (Long id, EditUserRequest request) {
        UserEntity userEntity = userRepo.getUserById(id);
        if (userEntity == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);

        userEntity.setUsername(request.username());
        userEntity.setEmail(request.email());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepo.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepo.getUserById(id);
        if (userEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);

        userRepo.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return userEntity;
    }   
}
