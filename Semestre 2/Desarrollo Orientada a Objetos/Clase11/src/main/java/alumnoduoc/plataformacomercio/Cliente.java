
package alumnoduoc.plataformacomercio;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Cliente {
	String idCliente, nombre;
	ArrayList<Pedido> pedidosRealizados;

	public Cliente(String idCliente, String nombre) {
		pedidosRealizados = new ArrayList<Pedido>();
		this.idCliente = idCliente;
		this.nombre = nombre;
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Pedido> getPedidosRealizados() {
		return pedidosRealizados;
	}
	
	public void addPedidosRealizados(Pedido newPedido) {
		pedidosRealizados.add(newPedido);
	}
}
