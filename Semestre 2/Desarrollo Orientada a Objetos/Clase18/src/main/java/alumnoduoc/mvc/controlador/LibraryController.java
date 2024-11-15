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
    public static ArrayList<Libro> usuarios = new ArrayList<>();
    public static ArrayList<Libro> prestamos = new ArrayList<>();
    
    static int newIdLibro = 1;
    static int newIdUsuario = 1;
    static int newIdPrestamo = 1;
    

    public static void main(String[] args) {
        vista.setVisible(true);
    }
    
    public static void registrarNuevoLibro( String titulo, String autor, String categoria) {
        inventario.add(new Libro(newIdLibro, titulo, autor, categoria));
        newIdLibro += 1;
    }
    
    public static void realizarPrestamo(Libro libro, Usuario usuario) {
        
    }
    
    public static void registrarDevolucion(Prestamo prestamo) {
        
    }
    
    public static void generarInformePrestamo() {
        
    }
    
}
