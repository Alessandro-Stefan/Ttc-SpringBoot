package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttc.app.entity.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    TaskEntity getTaskById(Long id);
}
