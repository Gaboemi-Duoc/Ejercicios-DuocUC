package com.example.demoDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoDB.model.Comuna;
import com.example.demoDB.service.ComunaService;

@RestController
@RequestMapping("api/v1/pacientes")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> listar(){
        List<Comuna> comuna = comunaService.findAll();
        if(comuna.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(comuna);
        }
    }
}
