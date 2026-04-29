package com.example.personal_bussiness_backend_pro.repository;


import com.example.personal_bussiness_backend_pro.model.ServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ServicePackageRepository extends JpaRepository<ServicePackage, Long> {
    List<ServicePackage> findAllByOrderByCreatedAtAsc();
}
