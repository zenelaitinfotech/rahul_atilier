package com.example.personal_bussiness_backend_pro.repository;

import com.example.personal_bussiness_backend_pro.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findAllByOrderByStepNumberAsc();
}

