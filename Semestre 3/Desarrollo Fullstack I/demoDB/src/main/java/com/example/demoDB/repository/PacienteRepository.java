package com.example.demoDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoDB.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
