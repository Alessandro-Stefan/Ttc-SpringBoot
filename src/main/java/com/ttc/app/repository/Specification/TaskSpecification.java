package com.ttc.app.repository.Specification;


import org.springframework.data.jpa.domain.Specification;

import com.ttc.app.dto.task.SearchTaskCriteria;
import com.ttc.app.entity.TaskEntity;

import jakarta.persistence.criteria.Predicate;

public class TaskSpecification {
    public static Specification<TaskEntity> byCriteria (SearchTaskCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();
            
            if (c.getUserId() != null)
                p = cb.and(p, cb.equal(root.get("user").get("id"), c.getUserId()));
        
            if (c.getTitle() != null)
                p = cb.and(p, cb.like(cb.lower(root.get("title")), "%" + c.getTitle().toLowerCase() + "%"));

            if (c.getDefinitionId() != null) 
                p = cb.and(p, cb.equal(root.get("taskDefinition").get("id"), c.getDefinitionId()));

            if (c.getPriority() != null) 
                p = cb.and(p, cb.equal(root.get("priority"), c.getPriority()));

            if (c.getCreatedAfter() != null) 
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("createdAt"), c.getCreatedAfter()));
                
            if (c.getCreatedBefore() != null) 
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("createdAt"), c.getCreatedBefore()));
            
            if (c.getUpdatedAfter() != null) 
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("updatedAt"), c.getUpdatedAfter()));
            
            if (c.getUpdatedBefore() != null)
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("updatedAt"), c.getUpdatedBefore()));

            if (c.getStatus() != null) 
                p = cb.and(p, cb.equal(root.get("status"), c.getStatus()));

            return p;
        };
    }
}
