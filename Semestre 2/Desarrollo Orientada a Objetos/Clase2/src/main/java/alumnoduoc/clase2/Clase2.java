/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.clase2;
import java.util.Scanner;

/**
 *
 * @author Gabo's
 */

public class Clase2 {

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		int exitOption = 5;
		int option = 0;
		int a,b,c;
		System.out.println("Bienvenido!");
		while (exitOption != option) {
			System.out.println("1. Suma");
			System.out.println("2. Resta");
			System.out.println("3. Multiplicar");
			System.out.println("4. Promedio");
			System.out.println("5. Salir");
			System.out.print("Ingrese una Opcion: ");
			option = read.nextInt();
			
			switch (option) {
				case 1:
					System.out.print("Ingrese un Numero: ");
					a = read.nextInt();
					System.out.print("Ingrese otro Numero: ");
					b = read.nextInt();
					suma(a,b);
					break;
				case 2:
					System.out.print("Ingrese un Numero: ");
					a = read.nextInt();
					System.out.print("Ingrese otro Numero: ");
					b = read.nextInt();
					c = a - b;
					resta(a,b);
					break;
				case 3:
					System.out.print("Ingrese un Numero: ");
					a = read.nextInt();
					System.out.print("Ingrese otro Numero: ");
					b = read.nextInt();
					multiplicacion(a,b);
					break;
				case 4:
					promedio(read);
					break;

				case 5:
					System.out.println("Saliendo, tenga un buen día...");
					break;
				default:
					System.out.println("Error, Ingrese un Valor Valido...");
			}
		
		
		}
	}
	
	static void suma(int a,int b) {
		int c = a + b;
		System.out.print("La Suma " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			System.out.println(c);
		}
	}

	static void resta(int a,int b) {
		int c = a - b;
		System.out.print("La Resta " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			System.out.println(c);
		}
	}
	
	static void multiplicacion(int a,int b) {
		int c = a * b;
		System.out.print("La Multiplicacion " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			System.out.println(c);
		}
	}
	
	static void promedio(Scanner read) {
		System.out.print("Cuantas Notas quiere ingresar?: ");
		int nNotas = read.nextInt();
		int[] notas = new int[nNotas];
		for (int i = 0; i < nNotas; i++) {
			System.out.print("Ingrese un Numero: ");
			notas[i] = read.nextInt();
		}
		int result = 0;
		for(int nota: notas){
			result += nota;
		}
		result /= nNotas; 

		System.out.print("El Promedio es de ");

		if(result % 1 == 0){
			int resultInt = (int)result;
			System.out.println(resultInt);
		} else {
			System.out.println(result);
		}
	}
}
