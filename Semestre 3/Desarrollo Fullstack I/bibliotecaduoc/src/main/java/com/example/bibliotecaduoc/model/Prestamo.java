package com.example.bibliotecaduoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor vacio.
public class Prestamo {
    private int id_prestamo;
    private int id_libro;
    private String run_solicitante;
    private String fecha_solicitud;
    private String fecha_entrega;
    private int cantidad_dias;
    private int multas = 0;
}
