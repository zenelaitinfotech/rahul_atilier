package com.example.personal_bussiness_backend_pro.repository;

import com.example.personal_bussiness_backend_pro.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    List<Testimonial> findAllByOrderByCreatedAtDesc();
}

