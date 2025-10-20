package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ttc.app.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository <UserEntity, Long>, JpaSpecificationExecutor<UserEntity>{
    UserEntity getUserById(Long id);
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
