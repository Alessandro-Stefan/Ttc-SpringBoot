package com.ttc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ttc.app.entity.TaskDefinitionEntity;

public interface TaskDefinitionRepo extends JpaRepository<TaskDefinitionEntity, Long> {

    TaskDefinitionEntity getTaskDefinitionById(Long id);
}
