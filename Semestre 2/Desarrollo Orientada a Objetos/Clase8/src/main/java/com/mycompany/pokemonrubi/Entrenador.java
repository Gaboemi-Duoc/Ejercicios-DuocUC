
package com.mycompany.pokemonrubi;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Entrenador {
	String nombre, genero;
	ArrayList<Pokemon> equipo=new ArrayList<>();

	public Entrenador(String nombre, String genero) {
		this.nombre = nombre;
		this.genero = genero;
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

	public void asociarEquipo(Pokemon poke) {
		equipo.add(poke);
		poke.setEntrenador(this);
	}
	public void quitarEquipo(Pokemon poke) {
		equipo.remove(poke);
		poke.setEntrenador(null);
	}

	@Override
	public String toString() {
		return "Entrenador{" + "nombre=" + nombre + ", genero=" + genero + '}';
	}
	
	
	
}
