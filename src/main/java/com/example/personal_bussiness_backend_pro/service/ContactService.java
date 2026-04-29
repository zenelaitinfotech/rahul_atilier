package com.example.personal_bussiness_backend_pro.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personal_bussiness_backend_pro.dto.ContactRequest;
import com.example.personal_bussiness_backend_pro.model.Contact;
import com.example.personal_bussiness_backend_pro.repository.ContactMessageRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ContactService {
	@Autowired
	private  ContactMessageRepository repo;
	private static final Logger log = LoggerFactory.getLogger(ContactService.class);
	public Contact saveMessage(ContactRequest request) {
        Contact msg = new Contact();
        msg.setName(request.getName());
        msg.setEmail(request.getEmail());
        msg.setPhone(request.getPhone());
        msg.setMessage(request.getMessage());
 	
        Contact saved = repo.save(msg);
        log.info("New contact message saved: id={}, from={}", saved.getId(), saved.getEmail());
        return saved;
    }
 
    public List<Contact> getAllMessages() {
        return repo.findAllByOrderByCreatedAtDesc();
    }
 
    public List<Contact> getNewMessages() {
        return repo.findByStatus(Contact.MessageStatus.NEW);
    }
 
    public Contact markAsRead(Long id) {
        Contact msg = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Message not found: " + id));
        msg.setStatus(Contact.MessageStatus.READ);
        return repo.save(msg);
    }

}
