package com.example.bibliotecaduoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bibliotecaduoc.model.Prestamo;
import com.example.bibliotecaduoc.service.PrestamoService;

import java.util.List;

//url: localhost:8080/
@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {
    
    @Autowired
    private PrestamoService PrestamoService;

	@GetMapping
	public List<Prestamo> listarPrestamos() {
		return PrestamoService.getPrestamos();
	}
	
	@PostMapping
	public Prestamo agregarPrestamo(@RequestBody Prestamo Prestamo) {
		return PrestamoService.savePrestamo(Prestamo);
	}

	@GetMapping("/{id}")
	public Prestamo buscarPrestamo(@PathVariable int id) {
		return PrestamoService.getPrestamoId(id);
	}

	@PutMapping("/{id}")
	public Prestamo actualizarPrestamo(@PathVariable int id, @RequestBody Prestamo Prestamo) {
		return PrestamoService.updatePrestamo(Prestamo);
	}

	@DeleteMapping("/{id}")
	public String eliminarPrestamo(@PathVariable int id) {
		return PrestamoService.deletePrestamo(id);
	}
}
