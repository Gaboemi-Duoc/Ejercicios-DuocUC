package com.example.bibliotecaduoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.service.LibroService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.getLibros();
    }
    
    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.saveLibro(libro);
    }

    @GetMapping("{id}")
    public Libro buscarLibro(@RequestParam int id) {
        return libroService.getLibroId(id);
    }
    
}
