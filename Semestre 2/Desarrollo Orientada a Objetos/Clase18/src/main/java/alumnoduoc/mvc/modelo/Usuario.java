/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.mvc.modelo;

import static alumnoduoc.mvc.controlador.LibraryController.prestamos;
import java.util.ArrayList;

/**
 *
 * @author pvespucio
 */
public class Usuario {
    static int newIdUsuario = 1;
    int idUsuario;
    String nombre;
    ArrayList<Prestamo> librosPrestados;

    public Usuario(String nombre, ArrayList<Prestamo> librosPrestados) {
        this.idUsuario = newIdUsuario;
        this.nombre = nombre;
        this.librosPrestados = librosPrestados;
        newIdUsuario += 1;
    }
    
    public void realizarPrestamo(Libro libro, String fechaPrestamo) {
        libro.prestarLibro();
        Prestamo newPrestamo = new Prestamo(libro, this, fechaPrestamo);
        prestamos.add(newPrestamo);
    }
    
    public ArrayList<Prestamo> verHistorialPrestamos() {
        return librosPrestados;
    }
}
