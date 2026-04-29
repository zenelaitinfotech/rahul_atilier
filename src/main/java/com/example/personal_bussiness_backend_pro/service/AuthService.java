package com.example.personal_bussiness_backend_pro.service;

import com.example.personal_bussiness_backend_pro.dto.*;
import com.example.personal_bussiness_backend_pro.model.User;
import com.example.personal_bussiness_backend_pro.repository.UserRepository;
import com.example.personal_bussiness_backend_pro.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
	@Autowired
    private  UserRepository userRepository;
	@Autowired
    private  PasswordEncoder passwordEncoder;
	@Autowired
    private  JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already taken");
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already registered");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAdmin(false); // always false for self-registration
        User saved = userRepository.save(user);

        String token = jwtUtil.generateToken(saved.getUsername(), saved.isAdmin());
        return new AuthResponse(token, saved.getUsername(), saved.getEmail(), saved.isAdmin(), saved.getId());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseGet(() -> userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password")));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.isAdmin());
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.isAdmin(), user.getId());
    }
}
