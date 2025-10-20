package com.ttc.app.dto.task;

import java.time.LocalDateTime;

public class SearchTaskCriteria {
    Long userId;
    String title;
    Long definitionId;
    Integer priority;
    LocalDateTime createdAfter;
    LocalDateTime createdBefore;
    LocalDateTime updatedAfter;
    LocalDateTime updatedBefore;
    Integer status;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getDefinitionId() {
        return definitionId;
    }
    public void setDefinitionId(Long definitionId) {
        this.definitionId = definitionId;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
	public LocalDateTime getCreatedAfter() {
		return createdAfter;
	}
	public void setCreatedAfter(LocalDateTime createdAfter) {
		this.createdAfter = createdAfter;
	}
	public LocalDateTime getCreatedBefore() {
		return createdBefore;
	}
	public void setCreatedBefore(LocalDateTime createdBefore) {
		this.createdBefore = createdBefore;
	}
	public LocalDateTime getUpdatedAfter() {
		return updatedAfter;
	}
	public void setUpdatedAfter(LocalDateTime updatedAfter) {
		this.updatedAfter = updatedAfter;
	}
	public LocalDateTime getUpdatedBefore() {
		return updatedBefore;
	}
	public void setUpdatedBefore(LocalDateTime updatedBefore) {
		this.updatedBefore = updatedBefore;
	}
    
}
