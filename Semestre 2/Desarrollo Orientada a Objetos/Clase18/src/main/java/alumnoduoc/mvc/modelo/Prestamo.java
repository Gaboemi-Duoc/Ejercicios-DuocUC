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
    static int newIdPrestamo = 1;
    int idPrestamo;
    Libro libroPrestado;
    Usuario usuario;
    String fechaPrestamo, fechaDevolucion;

    public Prestamo(Libro libroPrestado, Usuario usuario, String fechaPrestamo) {
        this.idPrestamo = newIdPrestamo;
        this.libroPrestado = libroPrestado;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = "";
        newIdPrestamo += 1;
    }
    
    public void registrarDevolucion(){
        this.libroPrestado.devolverLibro();
        this.fechaDevolucion = "fechaDevolucion";
    }
    public int calcularDiasPrestamo() {
        return 1;
    }
}
