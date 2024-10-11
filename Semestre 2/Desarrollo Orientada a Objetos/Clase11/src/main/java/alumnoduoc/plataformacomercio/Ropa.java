/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.plataformacomercio;

/**
 *
 * @author Gabo's
 */
public class Ropa extends Producto {
	String talla, color;

	public void ajustarTalla(String nuevaTalla) {
		talla = nuevaTalla;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getTalla() {
		return talla;
	}

	public Ropa(String talla, String color, String idProducto, String nombre, double precio) {
		super(idProducto, nombre, precio);
		this.talla = talla;
		this.color = color;
	}

	@Override
	public String getNombre() {
		return super.getNombre() + " " + color;
	}
	
	@Override
	public String toString() {
		return "\n" +idProducto + " " + nombre + " " + color +  " Talla " + talla + " | Precio = " + precio;
	}
}
