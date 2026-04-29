package com.example.personal_bussiness_backend_pro.controller;

import com.example.personal_bussiness_backend_pro.dto.ApiResponse;
import com.example.personal_bussiness_backend_pro.model.ServicePackage;
import com.example.personal_bussiness_backend_pro.repository.ServicePackageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {
	@Autowired
    private  ServicePackageRepository repo;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServicePackage>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("Fetched", repo.findAllByOrderByCreatedAtAsc()));
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<ServicePackage>> create(@RequestBody ServicePackage pkg) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Created", repo.save(pkg)));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<ServicePackage>> update(@PathVariable Long id, @RequestBody ServicePackage body) {
        ServicePackage p = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        p.setName(body.getName()); p.setDescription(body.getDescription());
        p.setPrice(body.getPrice()); p.setFeatures(body.getFeatures()); p.setPopular(body.isPopular());
        return ResponseEntity.ok(ApiResponse.ok("Updated", repo.save(p)));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted", null));
    }
}
