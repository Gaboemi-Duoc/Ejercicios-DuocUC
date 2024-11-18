/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.mvc.controlador;
import alumnoduoc.mvc.vista.LibraryGUI;
import alumnoduoc.mvc.modelo.*;
import java.util.ArrayList;

/**
 *
 * @author pvespucio
 */
public class LibraryController {
    static LibraryGUI vista = new LibraryGUI();
    public static ArrayList<Libro> inventario = new ArrayList<>();
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void main(String[] args) {
        vista.setVisible(true);
    }
    
    public static void registrarNuevoLibro( String titulo, String autor, String categoria) {
        inventario.add(new Libro(titulo, autor, categoria));
    }
    
    public static void realizarPrestamo(Libro libro, Usuario usuario) {
        if (inventario.contains(libro)) {
            usuario.realizarPrestamo(libro, "fechaPrestamo");
            
        } else {
            
        }
    }
    
    public static void registrarDevolucion(Prestamo prestamo) {
        
    }
    
    public static void generarInformePrestamo() {
        
    }
    
}
