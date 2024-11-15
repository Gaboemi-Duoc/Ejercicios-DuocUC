/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.mvc.modelo;

/**
 *
 * @author pvespucio
 */
public class Prestamo {
    int idPrestamo;
    Libro libroPrestado;
    Usuario usuario;
    String fechaPrestamo, fechaDevolucion;
    
    void registrarDevolucion(){
        
    }
    int calcularDiasPrestamo() {
        return 1;
    }
}
