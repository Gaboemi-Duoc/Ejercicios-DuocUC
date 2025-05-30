package com.example.demoDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoDB.model.Comuna;
import com.example.demoDB.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll(){
        return comunaRepository.findAll();
    }

    public Comuna findById(Long id){
        return comunaRepository.findById(id).get();
    }
    public Comuna save (Comuna comuna){
        return comunaRepository.save(comuna);
    }

    public void delete(Long id){
        comunaRepository.deleteById(id);
    }

}
