package com.ttc.app.service;

import com.ttc.app.dto.user.LoginRequest;
import com.ttc.app.dto.user.LoginResponse;

public interface AuthenticationService {
        LoginResponse login(LoginRequest request);
}
