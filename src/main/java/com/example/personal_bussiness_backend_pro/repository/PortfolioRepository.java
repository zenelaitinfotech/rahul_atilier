package com.example.personal_bussiness_backend_pro.repository;

import com.example.personal_bussiness_backend_pro.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findAllByOrderByCreatedAtDesc();
}
