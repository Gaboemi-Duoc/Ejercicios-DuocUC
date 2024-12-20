
package alumnoduoc.clase3;

import static alumnoduoc.clase3.Clase3.read;

/**
 *
 * @author Gabo's
 */
public class Menu {
	static void menu(){
		final int EXIT_OPTION = 7;
		int option = 0;
		while (EXIT_OPTION != option) {
			System.out.println("1. Suma");
			System.out.println("2. Resta");
			System.out.println("3. Multiplicar");
			System.out.println("4. Promedio");
			System.out.println("5. IVA");
			System.out.println("6. Descuento Salud y AFP");
			System.out.println( EXIT_OPTION +". Salir");
			System.out.print("Ingrese una Opcion: ");
			option = read.nextInt();
			
			switch (option) {
				case 1:
					System.out.println("Suma:");
					Resultado.suma();
					break;
				case 2:
					System.out.println("Resta:");
					Resultado.resta();
					break;
				case 3:
					System.out.println("Multiplicacion:");
					Resultado.multiplicacion();
					break;
				case 4:
					System.out.println("Promedio:");
					Resultado.promedio();
					break;
				case 5:
					System.out.println("IVA:");
					Resultado.iva();
					break;
				case 6:
					System.out.println("Descuento Salud y AFP");
					Resultado.descSaludAFP();
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
}
