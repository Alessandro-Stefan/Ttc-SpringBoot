package com.ttc.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ttc.app.dto.user.LoginRequest;
import com.ttc.app.dto.user.LoginResponse;
import com.ttc.app.util.JwtUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthenticationServiceImpl(JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (!auth.isAuthenticated())
            throw new UsernameNotFoundException("Invalid login credentials");

        return new LoginResponse(jwtUtil.generateToken(request.username()));
    }

    //TODO: TO implement login from OAuth2.0 ? ? ?
}
