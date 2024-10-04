/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.superrepaso;

/**
 *
 * @author Gabo's
 */
public class Entrenador {
    
   String nombre, genero;
   int codigo;

    public Entrenador(String nombre, String genero, int codigo) {
        this.nombre = nombre;
        this.genero = genero;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Entrenador{" + "nombre=" + nombre + ", genero=" + genero + ", codigo=" + codigo + '}';
    }
   
   
}
