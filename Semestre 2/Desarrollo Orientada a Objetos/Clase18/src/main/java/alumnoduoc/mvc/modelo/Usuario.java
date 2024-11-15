/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.mvc.modelo;

import java.util.ArrayList;

/**
 *
 * @author pvespucio
 */
public class Usuario {
    int idUsuario;
    String nombre;
    ArrayList<Prestamo> librosPrestados;
    
    void realizarPrestamo(Libro libro, String fechaPrestamo) {
        
    }
    
    ArrayList<Prestamo> verHistorialPrestamos() {
        return librosPrestados;
    }
}
