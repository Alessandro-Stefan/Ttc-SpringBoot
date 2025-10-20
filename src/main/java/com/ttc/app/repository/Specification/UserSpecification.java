package com.ttc.app.repository.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.ttc.app.dto.user.SearchUserCriteria;
import com.ttc.app.entity.UserEntity;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {
    public static Specification<UserEntity> byCriteria (SearchUserCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();
            
            if (c.getUsername() != null)
                p = cb.and(p, cb.like(cb.lower(root.get("username")), "%" + c.getUsername().toLowerCase() + "%"));
            
            if (c.getCreatedAfter() != null)
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("createdAt"), c.getCreatedAfter()));

            if (c.getCreatedBefore() != null)
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("createdAt"), c.getCreatedBefore()));
            if (c.getUpdatedAfter() != null)
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("updatedAt"), c.getUpdatedAfter()));
            if (c.getUpdatedBefore() != null)
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("updatedAt"), c.getUpdatedBefore()));
            if (c.getRole() != null)
                p = cb.and(p, cb.equal(root.get("role"), c.getRole()));
            return p;
        };
    }
}
