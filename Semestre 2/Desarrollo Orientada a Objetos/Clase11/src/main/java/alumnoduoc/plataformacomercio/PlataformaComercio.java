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
		new Electronico("Samsung", 12, "E1-S", "SmartTV", 500000),
		new Electronico("Samsung", 8, "E2-S", "SmartPhone", 450000),
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
			System.out.println(FINAL_OPTION + ". Salir");
			System.out.println("Ingrese una Opcion: ");
			System.out.print("> ");
			option = validaInt();
			switch (option) {
				case 1:
					System.out.println("Ingresando Cliente:");
					ingresarCliente();
					break;
				case 2:
					System.out.println("Creacion de Pedido:");
					crearPedido();
					break;
				case 3:
					System.out.println("Revision de Pedidos:");
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
		if (clientes.isEmpty()) {
			actualCliente = crearCliente();
		} else {
			boolean notSelected = true;
			while (notSelected) {
				for (Cliente cliente : clientes) {
					System.out.println(cliente.getIdCliente() + ". " + cliente.getNombre());
				}
				System.out.println("Ingrese el numero Cliente, si quiere crear un cliente ingrese -1: ");
				System.out.print("> ");
				int selection = validaInt();
				if (-1 == selection) {
					actualCliente = crearCliente();
					notSelected = false;
				} else {
					for (Cliente cliente : clientes) {
						System.out.println(cliente.getIdCliente() + ". " + cliente.getNombre());
						if (Integer.toString(selection).equals(cliente.getIdCliente())) {
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
	}
	static Cliente crearCliente() {
		System.out.println("Ingrese el Nombre del Cliente: ");
		System.out.print("> ");
		String name = leer.next();
		String idCliente = String.valueOf(clientes.size()+1);
		Cliente returnCliente = new Cliente(idCliente, name);
		clientes.add(returnCliente);
		System.out.println("Creado Cliente " + returnCliente.getNombre() + " N°" + returnCliente.getIdCliente());
		return returnCliente;
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
				Producto productoSeleccionado = ventaProductos[seleccion-1];
				if (productoSeleccionado.getClass() == Ropa.class) {
					System.out.println("Ingrese la Talla deseada: ");
					System.out.print("> ");
					String talla = leer.next();
					System.out.println("Ingrese el Color que deseada: ");
					System.out.print("> ");
					String color = leer.next();
					Ropa subProducto = new Ropa(talla, color, productoSeleccionado);
					productos.add(subProducto);
				} else {
					productos.add(productoSeleccionado);
				}

				System.out.println("Producto " + productoSeleccionado.getNombre() + " ha sido agregado al pedido exitosamente");
			} else {
				System.out.println("Seleccion no Valida!");
			}

		}

		actualCliente.addPedidosRealizados(new Pedido(productos));
		System.out.println("Pedido Realizado Exitosamente!");
	}
	static void revisarPedidos() {
		if (actualCliente != null) {
			int i = 1;
			for (Pedido pedido : actualCliente.getPedidosRealizados()) {
				System.out.println(i + "." + pedido);
				i += 1;
			}
		} else {
			System.out.println("No hay un Cliente Ingresado, porfavor ingrese un Cliente...");
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