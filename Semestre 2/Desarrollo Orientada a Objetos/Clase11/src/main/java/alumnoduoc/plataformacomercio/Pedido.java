
package alumnoduoc.plataformacomercio;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Pedido {
	ArrayList<Producto> productos;
	double total;

	public Pedido(ArrayList<Producto> productos) {
		this.productos = productos;
		for (Producto producto : productos) {
			this.total += producto.precio;
		}
	}

	public double calcularTotal() {
		double newTotal = 0;
		for (Producto producto : productos) {
			newTotal += producto.getPrecio();
		}
		this.total = newTotal;
		return total;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	@Override
	public String toString() {
		return "Pedido productos=" + productos + "\nTotal Pedido = " + total;
	}
	
	
}
