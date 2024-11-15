/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.mvc.modelo;

/**
 *
 * @author pvespucio
 */
public class Libro {
    int idLibro;
    String titulo, autor, categoria;
    boolean disponible;

    public Libro(int idLibro, String titulo, String autor, String categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = true;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    
    
    void prestarLibro(){
        
    }
    
    void devolverLibro() {
        
    }
    
    boolean getDisponibilidad() {
        return disponible;
    }
}
