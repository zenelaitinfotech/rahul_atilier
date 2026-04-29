package com.example.personal_bussiness_backend_pro.controller;

import com.example.personal_bussiness_backend_pro.dto.ApiResponse;
import com.example.personal_bussiness_backend_pro.model.Portfolio;
import com.example.personal_bussiness_backend_pro.repository.PortfolioRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
	@Autowired
    private  PortfolioRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Portfolio>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("Fetched", repo.findAllByOrderByCreatedAtDesc()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Portfolio>> getOne(@PathVariable Long id) {
        Portfolio p = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return ResponseEntity.ok(ApiResponse.ok("Fetched", p));
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Portfolio>> create(@RequestParam String title,
            @RequestParam String category,
            @RequestParam String location,
            @RequestParam String description,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) MultipartFile imageFile)throws Exception {
    	String finalImageUrl = imageUrl;

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "uploads/";
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFile.getBytes());

            finalImageUrl = "/uploads/" + fileName;
        }

        Portfolio portfolio = new Portfolio();
        portfolio.setTitle(title);
        portfolio.setCategory(category);
        portfolio.setLocation(location);
        portfolio.setDescription(description);
        portfolio.setImageUrl(finalImageUrl);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Created", repo.save(portfolio)));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Portfolio>> update (
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String category,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) MultipartFile imageFile
    ) throws Exception {

        Portfolio p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        p.setTitle(title);
        p.setCategory(category);
        p.setLocation(location);
        p.setDescription(description);

        if(imageFile != null && !imageFile.isEmpty()){
            String fileName = imageFile.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.write(path, imageFile.getBytes());
            p.setImageUrl("/uploads/" + fileName);
        } else {
            p.setImageUrl(imageUrl);
        }

        return ResponseEntity.ok(ApiResponse.ok("Updated", repo.save(p)));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }
}

