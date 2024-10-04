/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.plataformacomercio;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Gabo's
 */
public class PlataformaComercio {
	static Scanner leer =new Scanner(System.in).useLocale(Locale.ENGLISH);
	static Cliente actualCliente;
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static Producto[] ventaProductos = {
		new Electronico("Sony", 6, "E1-S", "WalkMan", 80000),
		new Electronico("Samsung", 12, "E1-S", "Samsung SmartTV", 500000),
		new Electronico("Samsung", 8, "E2-S", "Samsung SmartPhone", 450000),
		new Ropa(null, null, "R1", "Camisa", 25000),
		new Ropa(null, null, "R2", "Pantalon", 30000),
		new Ropa(null, null, "R3", "Chaqueta", 45000),
		new Producto("P1", "Ramen Instantaneo", 15000)
	};

	public static void main(String[] args) {
		final int FINAL_OPTION = 4;
		int option = -1;
		while (option != FINAL_OPTION) {
			if (actualCliente != null) {
				System.out.println("Hola " + actualCliente.getNombre() + "!");
			}
			System.out.println("1. Ingresar Cliente");
			System.out.println("2. Crear Pedido");
			System.out.println("3. Revisar Pedidos");
			option = validaInt();
			switch (option) {
				case 1:
					ingresarCliente();
					break;
				case 2:
					crearPedido();
					break;
				case 3:
					revisarPedidos();
					break;
				case FINAL_OPTION:
					System.out.println("Saliendo del Programa...");
					break;
			
				default:
					break;
			}
		}
	}

	static void ingresarCliente() {
		if (clientes.size() == 0) {
			actualCliente = crearCliente();
		} else {
			boolean notSelected = true;
			while (notSelected) {
				for (Cliente cliente : clientes) {
					System.out.println(cliente.getIdCliente() + ". " + cliente.getNombre());

				}
				String selection = leer.next();
				for (Cliente cliente : clientes) {
					System.out.println(cliente.getIdCliente() + ". " + cliente.getNombre());
					if (selection == cliente.getIdCliente()) {
						actualCliente = cliente;
						notSelected = false;
					}
				}
				if (notSelected) {
					System.out.println("Ingrese una opcion valida...");
				}
			}
		}
	}
	static Cliente crearCliente() {
		String name = leer.next();
		String idCliente = "C" + (clientes.size()+1);
		return new Cliente(idCliente, name);
	}

	static void crearPedido() {
		boolean comprando = true;
		ArrayList<Producto> productos = new ArrayList<>();
		while (comprando) {
			int i = 0;
			for (Producto producto : ventaProductos) {
				System.out.println((i+1)+". " + producto.getNombre() + " : " + producto.getPrecio());
				i += 1;
			}
			System.out.println("Ingrese el producto que quiera comprar, ingrese 0 para terminar la compra:");
			System.out.print("> ");
			int seleccion = validaInt();
			if(seleccion == 0) {
				comprando = false;
			}
			else if (seleccion > 0 && seleccion <= ventaProductos.length ) {
				Producto productoSeleccionado = ventaProductos[seleccion];
				if (productoSeleccionado.getClass() == Ropa.class) {
					System.out.println("!!");
				}

				productos.add(ventaProductos[seleccion]);
				System.out.println("Producto " + ventaProductos[seleccion].getNombre() + " ha sido agregado al pedido exitosamente");
			} else {
				System.out.println("Seleccion no Valida!");
			}

		}

		actualCliente.pedidosRealizados.add(new Pedido(productos));
	}
	static void revisarPedidos() {
		for (Pedido pedido : actualCliente.getPedidosRealizados()) {
			System.out.println(pedido);
		}
	}
	
	public static int validaInt(){
		int anInt;
		if (leer.hasNextInt()) {
			anInt = leer.nextInt();
		} else {
			anInt = -1;
			leer.nextLine();
		}
		return anInt;
	}
}
