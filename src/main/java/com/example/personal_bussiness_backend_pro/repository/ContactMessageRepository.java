package com.example.personal_bussiness_backend_pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.personal_bussiness_backend_pro.model.Contact;

@Repository
public interface ContactMessageRepository extends JpaRepository<Contact,Long> {
	List<Contact> findAllByOrderByCreatedAtDesc();
    List<Contact> findByStatus(Contact.MessageStatus status);

}
