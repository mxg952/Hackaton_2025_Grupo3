package com.hackato2025.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackato2025.demo.model.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long> {
    List<Formulario> findByBarrio(String barrio);
}