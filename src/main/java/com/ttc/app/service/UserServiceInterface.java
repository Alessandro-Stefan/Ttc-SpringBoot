package com.ttc.app.service;

import com.ttc.app.dto.user.*;

public interface UserServiceInterface {

    GetUserResponse getUserById(Long id);
    AddUserResponse addUser(AddUserRequest request);
}
