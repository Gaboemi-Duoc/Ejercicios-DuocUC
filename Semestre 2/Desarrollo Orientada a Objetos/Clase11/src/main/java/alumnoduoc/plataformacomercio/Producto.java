/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.plataformacomercio;

/**
 *
 * @author Gabo's
 */
public class Producto {
	String idProducto, nombre;
	double precio;

	public double calcularDescuento(double descuento) {
		return precio * (1-descuento);
	}

	public Producto(String idProducto, String nombre, double precio) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getIdProducto() {
		return idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "\n" +idProducto + " " + nombre + " | Precio = " + precio;
	}
	
}
