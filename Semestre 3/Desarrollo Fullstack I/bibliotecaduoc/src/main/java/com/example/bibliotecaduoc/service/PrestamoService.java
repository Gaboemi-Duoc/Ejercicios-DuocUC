package com.example.bibliotecaduoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaduoc.model.Prestamo;
import com.example.bibliotecaduoc.repository.PrestamoRepository;

@Service
public class PrestamoService {
	@Autowired
	private PrestamoRepository prestamoRepository;

	public List<Prestamo> getPrestamos() {
		return prestamoRepository.obtenerPrestamos();
	}

	public Prestamo savePrestamo(Prestamo Prestamo) {
		return prestamoRepository.guardar(Prestamo);
	}

	public Prestamo getPrestamoId(int id) {
		return prestamoRepository.buscarPorIdPrestamo(id);
	}

	public Prestamo updatePrestamo(Prestamo Prestamo) {
		return prestamoRepository.actualizar(Prestamo);
	}

	public String deletePrestamo(int id) {
		prestamoRepository.eliminar(id);
		return "producto eliminado";
	}
}
