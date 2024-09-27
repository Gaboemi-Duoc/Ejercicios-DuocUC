/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pokemonrubi;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pvespucio
 */
public class PokemonRubi {
	static Scanner leer =new Scanner(System.in);
	static ArrayList<Entrenador> entrenadores=new ArrayList<>();
	static ArrayList<Pokemon> pokemons=new ArrayList<>();

	public static void main(String[] args) {
		final int FINAL_OPTION = 7;
		boolean y=true;
		while(y){
			System.out.println("1.- Crear entrenador"); 
			System.out.println("2.- Crear pokemon"); 
			System.out.println("3.- Mostrar entre"); 
			System.out.println("4.- Mostrar pokemon"); 
			System.out.println("5.- Asociar pokemon"); 
			System.out.println("6.- Intercambiar pokemon"); 
			System.out.println(FINAL_OPTION+".- Salir del Programa"); 
			int op =Valida.validaInt();
			switch(op){
				case 1:
					creaEnt();

					break;
				case 2:
					creaPoke();
					break;
				case 3:
					muestraEnt();
					break;
				case 4:
					muestraPoke();
					break;
				case 5:
					asociaPoke();
					break;
				case 6:
					interPoke();
					break;
				case FINAL_OPTION:
					System.out.println("Saliendo del programa...");
					y=false;
					break;
				default:
					System.out.println("Elija una opcion válida...");
			}
		}
		
		

	}
	
	public static void creaEnt(){
		System.out.println("Creacion de Entrenador");
		System.out.println("Ingrese el nombre:");
		String nombre=leer.next();
		System.out.println("Ingrese el genero (M/F):");
		String gen=Valida.validaGen();
		Entrenador entrenador1=new Entrenador(nombre, gen);
		entrenadores.add(entrenador1);
	}
	
	public static void muestraEnt(){
		System.out.println("Mostrando Entrenadores:");
		for(Entrenador ent: entrenadores){
			System.out.println(ent);
		}
	}
	
	public static void creaPoke(){
		System.out.println("Creacion de Pokemon");
		System.out.println("Ingrese el nombre:");
		String nombre=leer.next();
		System.out.println("Ingrese el nivel:");
		int nivel=Valida.validaInt();
		Pokemon pokemon1=new Pokemon(nombre, nivel, null);
		pokemons.add(pokemon1);
	}
	
	public static void muestraPoke(){
		System.out.println("Mostrando Pokemones:");
		for(Pokemon ent: pokemons){
			System.out.println(ent);
		}
	}
	
	public static void asociaPoke(){
		System.out.println("Asociando un Pokemon:");
		int c=1;
		System.out.println("Seleccione un pokemon para asociar:");
		for(Pokemon pok: pokemons){
			System.out.println(c+".- "+pok.getNombre());
			c++;
		}
		int poke=Valida.validaInt();
		Pokemon thisPoke = pokemons.get(poke-1);
		System.out.println("Seleccione un entrenador para asociar:");
		c = 1;
		for(Entrenador ent: entrenadores){
			System.out.println(c+".- "+ent.getNombre());
			c++;
		}
		int ent=Valida.validaInt();
		Entrenador thisEnt = entrenadores.get(ent-1);
		thisEnt.asociarEquipo(thisPoke);
		System.out.println("El Pokemon " + thisPoke.getNombre() + " se ha asociado exitosamente con el entrenador " + thisEnt.getNombre());
	}
	
	public static void interPoke() {
		if (entrenadores.size() < 2) {
			System.out.println("No hay suficientes entrenadores para hacer un intercambio...");
		} else {
			System.out.println("Seleccione el entrenador que desea intercambiar:");
			int c = 1;
			for(Entrenador ent: entrenadores){
				System.out.println(c+".- "+ent.getNombre());
				c++;
			}
			int e = -1;
			while (e<1 || e>entrenadores.size()) {
				e=Valida.validaInt();
				if(e<1 || e>entrenadores.size()) {
					System.out.println("Opcion no valida, porfavor ingrese un Entrenador...");
				}
			}
			Entrenador entrenador1 = entrenadores.get(e-1);
			System.out.println("Seleccionado Entrenador " + entrenador1.getNombre());
			if (entrenador1.equipo.size() < 1) {
				Pokemon newPoke = new Pokemon("Charmander", e, null);
				entrenador1.asociarEquipo(newPoke);
				pokemons.add(newPoke);
			}
			System.out.println("Seleccione el otro entrenador que desea intercambiar:");
			c = 1;
			for(Entrenador ent: entrenadores){
				if (ent == entrenador1){

				} else {
					System.out.println(c+".- "+ent.getNombre());
					c++;
				}
			}
			int oldE = e;
			e = -1;
			while (e<1 || e>entrenadores.size()-1) {
				e=Valida.validaInt();
				if(e<1 || e>entrenadores.size()-1) {
					System.out.println("Opcion no valida, porfavor ingrese un Entrenador...");
				}
			}
			Entrenador entrenador2;
			if (e >= oldE) {
				entrenador2 = entrenadores.get(e);
			} else {
				entrenador2 = entrenadores.get(e-1);
			}
			System.out.println("Seleccionado Entrenador " + entrenador2.getNombre());
			if (entrenador2.equipo.size() < 1) {
				Pokemon newPoke = new Pokemon("Pikachu", e, null);
				entrenador2.asociarEquipo(newPoke);
				pokemons.add(newPoke);
			}

			c = 1;
			System.out.println("Entrenador " + entrenador1.getNombre() + ", elije el pokemon a intercambiar:");
			for (Pokemon poke : entrenador1.equipo) {
				System.out.println(c+".- "+poke.getNombre());
				c++;
			}e = -1;

			int p = -1;
			while (p<1 || p>entrenador1.equipo.size()) {
				p=Valida.validaInt();
				if(p<1 || p>entrenador1.equipo.size()) {
					System.out.println("Opcion no valida, porfavor ingrese un Pokemon...");
				}
			}
			
			Pokemon poke1 = entrenador1.equipo.get(p-1);

			c = 1;
			System.out.println("Entrenador " + entrenador2.getNombre() + ", elije el pokemon a intercambiar:");
			for (Pokemon poke : entrenador2.equipo) {
				System.out.println(c+".- "+poke.getNombre());
				c++;
			}
			p = -1;
			while (p<1 || p>entrenador2.equipo.size()) {
				p=Valida.validaInt();
				if(p<1 || p>entrenador2.equipo.size()) {
					System.out.println("Opcion no valida, porfavor ingrese un Pokemon...");
				}
			}
			Pokemon poke2 = entrenador2.equipo.get(p-1);

			entrenador1.quitarEquipo(poke1);
			entrenador2.quitarEquipo(poke2);

			entrenador1.asociarEquipo(poke2);
			entrenador2.asociarEquipo(poke1);
			System.out.println("Pokemones " + poke1.getNombre() + " y " + poke2.getNombre() + "han sido exitosamente intercambiados!");
		}
	}
}
