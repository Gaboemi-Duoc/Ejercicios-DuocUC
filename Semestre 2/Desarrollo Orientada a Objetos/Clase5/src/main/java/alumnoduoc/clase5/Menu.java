
package alumnoduoc.clase5;
import static alumnoduoc.clase5.Pizzeria.read;

/**
 *
 * @author Gabo's
 */
public class Menu {
	static void menu() {
		final int EXIT_OPTION = 5;
		int option = 0;

		Boleta boletaCliente = new Boleta();
		
		while (EXIT_OPTION != option) {
			System.out.println("1. Añadir Pizza");
			System.out.println("2. Añadir Palitos");
			System.out.println("3. Añadir Bebida");
			System.out.println("4. Generar Boleta");
			System.out.println( EXIT_OPTION +". Salir");
			System.out.print("Ingrese una Opcion: ");
			option = nextInt();
			System.out.println("");
			
			switch (option) {
				case 1:
					System.out.println("Añadir Pizza:");
					boletaCliente.addPizza(new Pizza());
					break;

				case 2:
					System.out.println("Añadir Palitos:");
					boletaCliente.addPalitos(new Palitos());
					break;

				case 3:
					System.out.println("Añadir Bebida:");
					boletaCliente.addBebida(new Bebida());
					break;

				case 4:
					System.out.println("Generar Boleta:");
					boletaCliente.printBoleta();
					break;

				case EXIT_OPTION:
					System.out.println("Saliendo, tenga un buen día...");
					break;
			
				default:
					System.out.println("Error, Ingrese un Valor Valido...");
					break;
			}
		}
	}

	// Validar Int
	public static int nextInt(){
		int anInt;
		if (read.hasNextInt()) {
			anInt = read.nextInt();
		} else {
			anInt = -1;
			read.nextLine();
		}
		return anInt;
	}


	// Ejemplo de try en Validor Int
	public static int tryNextInt(){
		int anInt;
		try {
			anInt = read.nextInt();
		} catch(Exception e) {
			anInt = -1;
			read.nextLine();
		}
		return anInt;
	}
}
