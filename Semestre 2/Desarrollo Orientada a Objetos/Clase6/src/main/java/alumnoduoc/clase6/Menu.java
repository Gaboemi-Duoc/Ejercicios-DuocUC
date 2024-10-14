
package alumnoduoc.clase6;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Menu { // Asociar Examen ; Pedir cliente para boleta
	static ArrayList<Client> clientList = new ArrayList<>();
	static ArrayList<Exam> examList = new ArrayList<>();
	static Scanner read = new Scanner(System.in).useLocale(Locale.US);
	static int curClient = 0;

	public static void menu() {
		final int EXIT_OPTION = 7;
		int option = 0;
		while (EXIT_OPTION != option) {
			System.out.println("1. Crear Cliente");
			System.out.println("2. Crear Examen");
			System.out.println("3. Asociar Examen");
			System.out.println("4. Listar Clientes");
			System.out.println("5. Listar Examenes");
			System.out.println("6. Crear Boleta");
			System.out.println( EXIT_OPTION +". Salir");
			System.out.print("Ingrese una Opcion: ");
			option = nextInt();
			System.out.println("");

			switch (option) {
				case 1:
					System.out.println("Crear Cliente:");
					createClient();
					break;

				case 2:
					System.out.println("Crear Examen:");
					createExamen();
					break;

				case 3:
					System.out.println("Asociar Examen:");
					setExamCode();
					break;

				case 4:
					System.out.println("Listar Clientes:");
					seeClients();
					break;

				case 5:
					System.out.println("Listar Examenes:");
					seeExams();
					break;

				case 6:
					System.out.println("Generar Boleta:");
					printBoleta();
					break;

				case EXIT_OPTION:
					System.out.println("Saliendo, tenga un buen día...");
					break;
			
				default:
					System.out.println("Error, Ingrese un Valor Valido...");
					break;
			}
		}
		
		read.close();
	}

	static void createClient() {
		int code = curClient;
		curClient += 1;
		System.out.print("Ingrese nombre del cliente: ");
		String name = read.next();
		System.out.print("Ingrese prevision de salud del cliente: ");
		String salud = read.next();
		System.out.println("Codigo del Cliente " + code);

		read.nextLine();
		Client newClient = new Client(code, name, salud);
		clientList.add(newClient);

		System.out.println("Cliente Creado Exitosamente.");
	}

	static void createExamen() {
		System.out.print("Ingrese nombre del examen: ");
		String name = read.next();
		System.out.print("Ingrese codigo del cliente asociado: ");

		boolean validateClient = false;

		while (validateClient == false) {
			int code = nextInt();
			for (Client client : clientList) {
				if (client.getCode() == code) {
					validateClient = true;
				}
			}

			if (validateClient) {
				System.out.print("Ingrese el costo del examen: ");
				int value = nextInt();
				
				read.nextLine();
				Exam newExam = new Exam(name, code, value);
				examList.add(newExam);
		
				System.out.println("Examen Creado Exitosamente.");
			} else {
				System.out.println("Cliente no existe, Examen Fallido.");
			}
		}
	}

	static void seeClients() {
		for (Client client : clientList) {
			System.out.println(client);
		}
	}
	static void seeExams() {
		for (Exam exam : examList) {
			System.out.println(exam);
		}
	}

	static void printBoleta() {
		System.out.print("Ingrese codigo del cliente: ");
		int code = nextInt();
		int total = 0;
		String clientName = "";

		boolean validateClient = false;
		for (Client client : clientList) {
			if (client.getCode() == code) {
				validateClient = true;
				clientName = client.getNombre();
			}
		}

		if(validateClient) {

			System.out.println("Boleta de " + clientName);
			for (Exam exam : examList) {
				if (exam.getCode() == code) {
					int value = exam.getValue();
					System.out.println( exam.getName()+": "+ value);
					total += value;
				}
			}
			System.out.println( "Total: "+ total);
		} else {
			System.out.println( "Error! Cliente no existe");
		}
	}

	static void setExamCode() {
		int n = 1;
		for (Exam exam : examList) {
			System.out.println(n + ". " + exam.name + " - Codigo " + exam.code);
			n += 1;
		}
		System.out.print("Ingrese el examen el cual quiera cambiar: ");
		int opc = nextInt() - 1;
		if (opc <= examList.size() && opc >= 0) {
			System.out.print("Ingrese el codigo del cliente al que quiere asociar: ");
			int code = nextInt();
			
			boolean validateClient = false;
			for (Client client : clientList) {
				if (client.getCode() == code) {
					validateClient = true;
				}
			}
			if(validateClient) {
				examList.get(opc).setCode(code);
			} else {
				System.out.println("Error! Cliente no Existe");
			}

			System.out.println("Examen Asociado Exitosamente.");
		} else {
			System.out.println("Examen invalido!");
		}
		
	}

	// Validar Int
	public static int nextInt() {
		int anInt;
		if (read.hasNextInt()) {
			anInt = read.nextInt();
		} else {
			anInt = -1;
			read.nextLine();
		}
		
		return anInt;
	}
}
