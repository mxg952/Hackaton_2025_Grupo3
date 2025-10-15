package com.hackato2025.demo.repository;

import com.hackato2025.demo.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    List<Newsletter> findByActivoTrue();
    boolean existsByCorreo(String correo);
    Newsletter findByCorreo(String correo);
}