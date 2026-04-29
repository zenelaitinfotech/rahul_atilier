package com.example.personal_bussiness_backend_pro.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity @Table(name = "portfolio")
public class Portfolio {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public Portfolio() {
		
	}
	public Portfolio(Long id, String title, String category, String location, String description, String imageUrl,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.location = location;
		this.description = description;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String category;
    private String location;
    @Column(columnDefinition = "TEXT") private String description;
    private String imageUrl;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
