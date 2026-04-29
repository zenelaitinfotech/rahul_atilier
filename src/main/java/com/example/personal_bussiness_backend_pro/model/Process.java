package com.example.personal_bussiness_backend_pro.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity @Table(name = "processes")
public class Process {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Process() {
    	
    }
    public Process(Long id, int stepNumber, String title, String description, String icon, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.stepNumber = stepNumber;
		this.title = title;
		this.description = description;
		this.icon = icon;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Column(nullable = false) private int stepNumber;
    @Column(nullable = false) private String title;
    @Column(columnDefinition = "TEXT") private String description;
    private String icon;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}

