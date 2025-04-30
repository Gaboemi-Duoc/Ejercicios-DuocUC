package com.example.bibliotecaduoc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.bibliotecaduoc.model.Prestamo;

@Repository
public class PrestamoRepository {

	private List<Prestamo> listaPrestamos = new ArrayList<>();

	public PrestamoRepository() {}

	public List<Prestamo> obtenerPrestamos() {
		return listaPrestamos;
	}

	public Prestamo guardar(Prestamo prestamo) {
		listaPrestamos.add(prestamo);
		return prestamo;
	}

	public Prestamo buscarPorIdPrestamo(int id) {
		for(Prestamo prestamo : listaPrestamos) {
			if (prestamo.getId_prestamo() == id) {
				return prestamo;
			}
		}
		return null;
	}

	public Prestamo actualizar(Prestamo pres) {
		int id = 0;
		int idPosicion = 0;

		for (int i = 0; i < listaPrestamos.size(); i++) {
			if (listaPrestamos.get(i).getId_prestamo() == pres.getId_prestamo()) {
				id = pres.getId_prestamo();
				idPosicion = i;
			}
		}

		Prestamo presta1 = new Prestamo();
		presta1.setId_prestamo(id);
		presta1.setId_libro(pres.getId_libro());
		presta1.setRun_solicitante(pres.getRun_solicitante());
		presta1.setFecha_solicitud(pres.getFecha_solicitud());
		presta1.setFecha_entrega(pres.getFecha_entrega());
		presta1.setCantidad_dias(pres.getCantidad_dias());
		presta1.setMultas(pres.getMultas());

		listaPrestamos.set(idPosicion, presta1);
		return presta1;
	}

	public void eliminar(int id) {
		// Alternativa 1
		// Prestamo prestamo = buscarPorIdPrestamo(id);
		// if (prestamo != null) {
		// 	listaPrestamos.remove(prestamo);
		// }

		// Alternativa 3
		listaPrestamos.removeIf(x -> x.getId_prestamo() == id);
	}
}
