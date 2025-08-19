package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttc.app.dto.user.AddUserResponse;
import com.ttc.app.entity.UserEntity;

public interface UserRepo extends JpaRepository <UserEntity, Long>{

    UserEntity getUserById(Long id);
}
