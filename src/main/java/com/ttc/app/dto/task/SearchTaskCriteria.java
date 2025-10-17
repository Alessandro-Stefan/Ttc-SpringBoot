package com.ttc.app.dto.task;

import java.time.LocalDateTime;

public class SearchTaskCriteria {
    Long userId;
    String title;
    Long definitionId;
    Integer priority;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
