package com.ttc.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ttc.app.dto.user.LoginRequest;
import com.ttc.app.dto.user.LoginResponse;
import com.ttc.app.entity.Role;
import com.ttc.app.entity.UserEntity;
import com.ttc.app.repository.UserRepo;
import com.ttc.app.util.JwtUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;

    public AuthenticationServiceImpl(JwtUtil jwtUtil, AuthenticationManager authManager, UserRepo userRepo) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userRepo = userRepo;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (!auth.isAuthenticated())
            throw new UsernameNotFoundException("Invalid login credentials");

        return new LoginResponse(jwtUtil.generateToken(request.username()));
    }

    @Override
    public boolean checkUserAuthorization(String jwtToken, Long resourceOwnerId) {
        String username = jwtUtil.extractUsername(jwtUtil.extractTokenValue(jwtToken));
        UserEntity user = userRepo.findByUsername(username);

        if (user.getId() == resourceOwnerId) return true;
        if (resourceOwnerId == 1) return true;
        if (user.getRole() == Role.ROLE_ADMIN) return true;

        return false; 
    }

    @Override
    public Long getUserIdFromToken(String jwtToken) {
        String username = jwtUtil.extractUsername(jwtUtil.extractTokenValue(jwtToken));
        UserEntity user = userRepo.findByUsername(username);
        
        return user.getId();
    }

    @Override
    public boolean checkAdminAuthorization(String jwtToken) {
        String username = jwtUtil.extractUsername(jwtUtil.extractTokenValue(jwtToken));
        UserEntity user = userRepo.findByUsername(username);

        return user.getRole() == Role.ROLE_ADMIN;
    }
    //TODO: TO implement login from OAuth2.0 ? ? ?
}
