/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemonrubi;

import static com.mycompany.pokemonrubi.PokemonRubi.leer;

/**
 *
 * @author pvespucio
 */
public class Valida {
	
	public static int validaInt(){
		String input;
		int num = 0;
		while(true){
			try{
				System.out.print("> ");
				input = leer.next();
				num = Integer.parseInt(input);
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("Error, Ingrese un número entero.");
			}
		}

		return num;
	}
	
	public static String validaGen() {
		boolean x=true;
		String genero="";
		while(x) {
			System.out.print("> ");
			genero=leer.next();
			if("m".equals(genero.toLowerCase())||"f".equals(genero.toLowerCase())){
				x=false;
				
			} else{
				System.out.println("Invalido, solo debe ingrese M o F");
			}
		}
		return genero;
	}
	
}
