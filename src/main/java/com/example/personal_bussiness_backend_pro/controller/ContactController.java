package com.example.personal_bussiness_backend_pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.personal_bussiness_backend_pro.dto.ApiResponse;
import com.example.personal_bussiness_backend_pro.dto.ContactRequest;
import com.example.personal_bussiness_backend_pro.model.Contact;
import com.example.personal_bussiness_backend_pro.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor

public class ContactController {
	@Autowired
	private ContactService contactService;
	
    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> submitContact(
            @Valid @RequestBody ContactRequest request) {
 
        Contact saved = contactService.saveMessage(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.ok("Message sent successfully!", saved));
    }
 
   
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getAllMessages() {
        List<Contact> messages = contactService.getAllMessages();
        return ResponseEntity.ok(ApiResponse.ok("Messages fetched", messages));
    }
 
   
    @GetMapping("/new")
    public ResponseEntity<ApiResponse<List<Contact>>> getNewMessages() {
        return ResponseEntity.ok(
            ApiResponse.ok("New messages", contactService.getNewMessages())
        );
    }
 
    
    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Contact>> markRead(@PathVariable Long id) {
        return ResponseEntity.ok(
            ApiResponse.ok("Marked as read", contactService.markAsRead(id))
        );
    }

	
}
