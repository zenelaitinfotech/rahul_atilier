package com.example.personal_bussiness_backend_pro.controller;

import com.example.personal_bussiness_backend_pro.dto.ApiResponse;
import com.example.personal_bussiness_backend_pro.model.Testimonial;
import com.example.personal_bussiness_backend_pro.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@RequiredArgsConstructor
public class TestimonialController {
	@Autowired
    private  TestimonialRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Testimonial>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("Fetched", repo.findAllByOrderByCreatedAtDesc()));
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Testimonial>> create(@RequestBody Testimonial t) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Created", repo.save(t)));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Testimonial>> update(@PathVariable Long id, @RequestBody Testimonial body) {
        Testimonial t = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        t.setClientName(body.getClientName()); t.setClientRole(body.getClientRole());
        t.setContent(body.getContent()); t.setRating(body.getRating());
        return ResponseEntity.ok(ApiResponse.ok("Updated", repo.save(t)));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }
}