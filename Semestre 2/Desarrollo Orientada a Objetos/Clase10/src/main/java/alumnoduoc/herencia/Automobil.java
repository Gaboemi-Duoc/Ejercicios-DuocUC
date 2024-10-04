/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.herencia;

/**
 *
 * @author Gabo's
 */
public class Automobil extends Vehiculo {
	int numPuertas;

	public Automobil(int numPuertas, int velocidadMax, String marca, String modelo) {
		super(marca, modelo, velocidadMax);
		this.numPuertas = numPuertas;
	}

	@Override
	public String toString() {
		return "Automobil{" + "numPuertas=" + numPuertas + ", marca=" + marca + ", modelo=" + modelo + '}';
	}
	
	
}
