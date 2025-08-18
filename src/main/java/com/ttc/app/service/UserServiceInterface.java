package com.ttc.app.service;

import com.ttc.app.dto.user.GetUserResponse;
import com.ttc.app.dto.user.UserDto;

public interface UserServiceInterface {

    GetUserResponse getUserById(Long id);
}
