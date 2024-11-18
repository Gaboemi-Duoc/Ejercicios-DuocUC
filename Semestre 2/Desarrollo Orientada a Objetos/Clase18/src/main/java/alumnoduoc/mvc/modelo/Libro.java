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
    static int newIdLibro = 1;
    int idLibro;
    String titulo, autor, categoria;
    boolean disponible;

    public Libro(String titulo, String autor, String categoria) {
        this.idLibro = newIdLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = true;
        newIdLibro += 1;
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
    
    
    
    public void prestarLibro(){
        if (!disponible) {
            System.out.println("Este libro ya esta prestado!");
        } else {
            disponible = false;
        }
    }
    
    public void devolverLibro() {
        if (disponible) {
            System.out.println("Este libro ya esta en el inventario");
        } else {
            disponible = true;
        }
    }
    
    boolean getDisponibilidad() {
        return disponible;
    }
}
