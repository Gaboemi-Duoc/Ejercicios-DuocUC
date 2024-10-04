/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.plataformacomercio;

/**
 *
 * @author Gabo's
 */
public class Electronico extends Producto {
	String marca;
	int garantiaMeses;

	public void extenderGarantia(int mesesExtra) {
		garantiaMeses += mesesExtra;
		//System.out.println("Garantia extendida por " + mesesExtra + " meses! Meses restantes: " + garantiaMeses);
	}

	public Electronico(String marca, int garantiaMeses, String idProducto, String nombre, double precio) {
		super(idProducto, nombre, precio);
		this.marca = marca;
		this.garantiaMeses = garantiaMeses;
	}
	
	
}
