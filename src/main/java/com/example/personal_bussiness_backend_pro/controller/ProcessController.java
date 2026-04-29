package com.example.personal_bussiness_backend_pro.controller;

import com.example.personal_bussiness_backend_pro.dto.ApiResponse;
import com.example.personal_bussiness_backend_pro.model.Process;
import com.example.personal_bussiness_backend_pro.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {
	@Autowired
    private  ProcessRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Process>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("Fetched", repo.findAllByOrderByStepNumberAsc()));
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Process>> create(@RequestBody Process process) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Created", repo.save(process)));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Process>> update(@PathVariable Long id, @RequestBody Process body) {
        Process p = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        p.setStepNumber(body.getStepNumber()); p.setTitle(body.getTitle());
        p.setDescription(body.getDescription()); p.setIcon(body.getIcon());
        return ResponseEntity.ok(ApiResponse.ok("Updated", repo.save(p)));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }
}