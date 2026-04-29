package com.example.personal_bussiness_backend_pro.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank private String username;
    public LoginRequest(@NotBlank String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
	}
    public LoginRequest() {
    	
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@NotBlank private String password;
}