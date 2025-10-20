package com.ttc.app.dto.taskDefinition;

import java.time.LocalDateTime;

public class SearchTaskDefinitionCriteria {
    Long userId;
    String category;
    String description;
    LocalDateTime createdAfter;
    LocalDateTime updatedAfter;
    LocalDateTime createdBefore;
    LocalDateTime updatedBefore;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	public LocalDateTime getCreatedAfter() {
		return createdAfter;
	}
	public void setCreatedAfter(LocalDateTime createdAfter) {
		this.createdAfter = createdAfter;
	}
	public LocalDateTime getUpdatedAfter() {
		return updatedAfter;
	}
	public void setUpdatedAfter(LocalDateTime updatedAfter) {
		this.updatedAfter = updatedAfter;
	}
	public LocalDateTime getCreatedBefore() {
		return createdBefore;
	}
	public void setCreatedBefore(LocalDateTime createdBefore) {
		this.createdBefore = createdBefore;
	}
	public LocalDateTime getUpdatedBefore() {
		return updatedBefore;
	}
	public void setUpdatedBefore(LocalDateTime updatedBefore) {
		this.updatedBefore = updatedBefore;
	}
    
}
