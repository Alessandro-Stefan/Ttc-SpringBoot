package com.ttc.app.repository.Specification;


import org.springframework.data.jpa.domain.Specification;

import com.ttc.app.dto.task.SearchTaskCriteria;
import com.ttc.app.entity.TaskEntity;

import jakarta.persistence.criteria.Predicate;

public class TaskSpecification {
    public static Specification<TaskEntity> byCriteria (SearchTaskCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();
            p = cb.and(p, cb.equal(root.get("user").get("id"), c.getUserId()));
        
            if (c.getTitle() != null)
                p = cb.and(p, cb.like(cb.lower(root.get("title")), "%" + c.getTitle().toLowerCase() + "%"));

            if (c.getDefinitionId() != null) 
                p = cb.and(p, cb.equal(root.get("taskDefinition").get("id"), c.getDefinitionId()));

            if (c.getPriority() != null) 
                p = cb.and(p, cb.equal(root.get("priority"), c.getPriority()));

            if (c.getCreatedAt() != null) 
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("createdAt"), c.getCreatedAt()));

            if (c.getUpdatedAt() != null) 
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("updatedAt"), c.getUpdatedAt()));

            if (c.getStatus() != null) 
                p = cb.and(p, cb.equal(root.get("status"), c.getStatus()));

            return p;
        };
    }
}
