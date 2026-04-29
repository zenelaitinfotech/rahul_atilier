package com.example.personal_bussiness_backend_pro.dto;

import java.time.LocalDateTime;

public class UserDto {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public UserDto() {
		
	}
	public UserDto(Long id, String username, String email, boolean isAdmin, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
		this.createdAt = createdAt;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	private Long id;
    private String username;
    private String email;
    private boolean isAdmin;
    private LocalDateTime createdAt;
}

