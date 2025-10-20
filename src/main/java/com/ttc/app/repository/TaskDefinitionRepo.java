package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ttc.app.entity.TaskDefinitionEntity;

@Repository
public interface TaskDefinitionRepo extends JpaRepository<TaskDefinitionEntity, Long>, JpaSpecificationExecutor<TaskDefinitionEntity> {
    TaskDefinitionEntity getTaskDefinitionById(Long id);
}
