
package com.mycompany.superrepaso;

/**
 *
 * @author Gabo's
 */
public class Pokemon {
    String nombre, tipo ;
    int nivel;
    Entrenador Entrenador;

    public Pokemon(String nombre, String tipo, int nivel, Entrenador Entrenador) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.Entrenador = Entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Entrenador getEntrenador() {
        return Entrenador;
    }

    public void setEntrenador(Entrenador Entrenador) {
        this.Entrenador = Entrenador;
    }



    @Override
    public String toString() {
        return "Pokemon{" + "nombre=" + nombre + ", tipo=" + tipo + ", nivel=" + nivel + ", nombreEntrenador=" + Entrenador + '}';
    }

    
    
    
    
    
}
