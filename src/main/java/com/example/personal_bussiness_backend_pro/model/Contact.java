package com.example.personal_bussiness_backend_pro.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Entity
@Table(name = "contact_messages")

public class Contact {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	public Contact() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    public Contact(Long id, @NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			String phone, @NotBlank(message = "Message is required") String message, LocalDateTime createdAt,
			MessageStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.message = message;
		this.createdAt = createdAt;
		this.status = status;
	}

	@NotBlank(message = "Name is required")
    @Column(nullable = false, length = 100)
    private String name;
 
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, length = 255)
    private String email;
 
    @Column(length = 20)
    private String phone;
 
    @NotBlank(message = "Message is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status = MessageStatus.NEW;
 
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
 
    public enum MessageStatus {
        NEW, READ, REPLIED
    }

}
