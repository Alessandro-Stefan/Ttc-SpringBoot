package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttc.app.entity.TaskEntity;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    TaskEntity getTaskById(Long id);
}
