 package com.example.personal_bussiness_backend_pro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
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
		public void setEmail(String email) {
			this.email = email;
		}
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		private String token;
	    private String username;
	    public AuthResponse() {
	    	
	    }
	    public AuthResponse(String token, String username, String email, boolean isAdmin, Long id) {
			super();
			this.token = token;
			this.username = username;
			this.email = email;
			this.isAdmin = isAdmin;
			this.id = id;
		}
		private String email;
		@JsonProperty("isAdmin")
	    private boolean isAdmin;
	    private Long id;
	}



