package com.ttc.app.repository.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.ttc.app.dto.taskDefinition.SearchTaskDefinitionCriteria;
import com.ttc.app.entity.TaskDefinitionEntity;

import jakarta.persistence.criteria.Predicate;

public class TaskDefinitionSpecification {

    public static Specification<TaskDefinitionEntity> byCriteria(SearchTaskDefinitionCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();
            
            if (c.getUserId() != null)
                p = cb.and(p, cb.or(cb.equal(root.get("user").get("id"), c.getUserId()),
                                    cb.equal(root.get("user").get("id"), Long.valueOf(1)))); 

            if (c.getCategory() != null) 
                p = cb.and(p, cb.equal(root.get("category"), c.getCategory()));

            if(c.getDescription() != null)
                p = cb.and(p, cb.like(cb.lower(root.get("description")), "%" + c.getDescription().toLowerCase() + "%"));

            if(c.getCreatedAfter() != null)
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("createdAt"), c.getCreatedAfter()));

            if(c.getUpdatedAfter() != null)
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("updatedAt"), c.getUpdatedAfter()));

            if(c.getCreatedBefore() != null)
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("createdAt"), c.getCreatedBefore()));

            if(c.getUpdatedBefore() != null)
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("updatedAt"), c.getUpdatedBefore()));

            return p;
        };
    }
}
