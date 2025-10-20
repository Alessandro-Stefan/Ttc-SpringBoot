package com.ttc.app.dto.user;

import java.time.LocalDateTime;

public class SearchUserCriteria {
    String username;
    LocalDateTime createdAfter;
    LocalDateTime createdBefore;
    LocalDateTime updatedAfter;
    LocalDateTime updatedBefore;
    String role;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

    
}
