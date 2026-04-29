package com.example.personal_bussiness_backend_pro.config;

import com.example.personal_bussiness_backend_pro.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ✅ FIX 1: Enable CORS — without this, Spring Security blocks all
            // OPTIONS preflight requests before your CorsConfig ever runs.
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // ✅ FIX 2: Permit ALL OPTIONS preflight requests first
                // Browsers send OPTIONS before every cross-origin request.
                // If OPTIONS is blocked, the actual GET/POST never fires.
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Auth — always public
                .requestMatchers("/api/auth/**").permitAll()

                // Static uploads — always public
                .requestMatchers("/uploads/**").permitAll()

                // ✅ FIX 3: Admin sub-paths MUST come BEFORE the broad GET permitAll rules.
                // Spring Security matches rules top-to-bottom and stops at first match.
                // If "GET /api/portfolio/**" permitAll comes first, it matches
                // "GET /api/portfolio/admin/..." too — making admin routes publicly readable.
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/portfolio/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/testimonials/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/packages/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/process/admin/**").hasRole("ADMIN")

                // Contact: POST public, GET/PUT admin only
                .requestMatchers(HttpMethod.POST, "/api/contact").permitAll()
                .requestMatchers(HttpMethod.GET,  "/api/contact/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/api/contact/**").hasRole("ADMIN")

                // Public read for content (non-admin paths only, after admin rules above)
                .requestMatchers(HttpMethod.GET, "/api/portfolio/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/testimonials/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/packages/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/process/**").permitAll()

                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}
