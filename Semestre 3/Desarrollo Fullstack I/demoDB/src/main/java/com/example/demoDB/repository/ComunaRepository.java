package com.example.demoDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoDB.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna,Long> {
    

}
