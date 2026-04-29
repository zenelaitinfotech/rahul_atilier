package com.example.personal_bussiness_backend_pro.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity @Table(name = "testimonials")
public class Testimonial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Testimonial(Long id, String clientName, String clientRole, String content, int rating,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientRole = clientRole;
		this.content = content;
		this.rating = rating;
		this.createdAt = createdAt;
	}
    public Testimonial() {
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientRole() {
		return clientRole;
	}
	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Column(nullable = false) private String clientName;
    private String clientRole;
    @Column(columnDefinition = "TEXT", nullable = false) private String content;
    private int rating = 5;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
