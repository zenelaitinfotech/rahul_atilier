package com.example.personal_bussiness_backend_pro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {
	
    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;
 
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    @Size(max = 255)
    private String email;
 
    @Size(max = 20)
    private String phone;
 
    @NotBlank(message = "Message is required")
    @Size(max = 1000)
    private String message;


}
