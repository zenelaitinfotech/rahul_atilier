package com.example.personal_bussiness_backend_pro.controller;

import com.example.personal_bussiness_backend_pro.dto.*;
import com.example.personal_bussiness_backend_pro.model.User;
import com.example.personal_bussiness_backend_pro.repository.UserRepository;
import com.example.personal_bussiness_backend_pro.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
    private  UserRepository userRepository;
	@Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userRepository.findAll().stream().map(u ->
            new UserDto(u.getId(), u.getUsername(), u.getEmail(), u.isAdmin(), u.getCreatedAt())
        ).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok("Users fetched", users));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<ApiResponse<UserDto>> updateRole(
            @PathVariable Long id,
            @RequestParam boolean isAdmin) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setAdmin(isAdmin);
        userRepository.save(user);
        UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.isAdmin(), user.getCreatedAt());
        return ResponseEntity.ok(ApiResponse.ok("Role updated", dto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("User deleted", null));
    }
}
