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

	public Ropa(String talla, String color, String idProducto, String nombre, double precio) {
		super(idProducto, nombre, precio);
		this.talla = talla;
		this.color = color;
	}
	
	public Ropa(Producto producto) {
		super(producto.getIdProducto(), producto.getNombre(), producto.getPrecio());
		this.talla = null;
		this.color = null;
	}
}
