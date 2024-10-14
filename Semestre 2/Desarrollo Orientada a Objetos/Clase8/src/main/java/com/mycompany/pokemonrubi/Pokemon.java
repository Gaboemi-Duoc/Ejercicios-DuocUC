
package com.mycompany.pokemonrubi;

/**
 *
 * @author Gabo's
 */
public class Pokemon {
	String nombre;
	int nivel;
	Entrenador entrenador;

	public Pokemon(String nombre, int nivel, Entrenador entrenador) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.entrenador = entrenador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "Pokemon{" + "nombre=" + nombre + ", nivel=" + nivel + ", entrenador=" + entrenador + '}';
	}

	
	
	
}
