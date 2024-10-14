
package alumnoduoc.clase3;

import static alumnoduoc.clase3.Clase3.read;

/**
 *
 * @author Gabo's
 */
public class Resultado {
	static void suma() {
		System.out.print("Ingrese un Numero: ");
		double a = read.nextDouble();
		System.out.print("Ingrese otro Numero: ");
		double b = read.nextDouble();
		double c = a + b;
		System.out.print("La Suma " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			c = Math.round(c * 100) / 100f;
			System.out.println(c);
		}
	}

	static void resta() {
		System.out.print("Ingrese un Numero: ");
		double a = read.nextDouble();
		System.out.print("Ingrese otro Numero: ");
		double b = read.nextDouble();
		double c = a - b;
		System.out.print("La Resta " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			c = Math.round(c * 100) / 100f;
			System.out.println(c);
		}
	}

	static void multiplicacion() {
		System.out.print("Ingrese un Numero: ");
		double a = read.nextDouble();
		System.out.print("Ingrese otro Numero: ");
		double b = read.nextDouble();
		double c = a * b;
		System.out.print("La Multiplicacion " + a + " + " + b + " es igual a ");
		if(c % 1 == 0){
			int cInt = (int)c;
			System.out.println(cInt);
		} else {
			c = Math.round(c * 100) / 100f;
			System.out.println(c);
		}
	}
	
	static void promedio() {
		System.out.print("Cuantas Notas quiere ingresar?: ");
		int nNotas = read.nextInt();
		double result = 0;
		for (int i = 0; i < nNotas; i++) {
			System.out.print("Ingrese una Nota: ");
			result += read.nextDouble();
		}
		result /= nNotas; 

		System.out.print("El Promedio es de ");

		if(result % 1 == 0){
			int resultInt = (int)result;
			System.out.println(resultInt);
		} else {
			result = Math.round(result * 100) / 100f;
			System.out.println(result);
		}
	}
	
	static void iva() {
		System.out.print("Cuanto es su compra?: ");
		double compra = read.nextDouble();
		compra = Math.round(compra * 100) / 100f;
		System.out.println("Con el 19% de la compra el total seria de " + (compra * 1.19) );
	}
	
	static double descSalud(double sueldo) {
		return sueldo * 0.07;
	}
	
	static double descAFP(double sueldo) {
		return sueldo * 0.13;
	}
	
	static void descSaludAFP() {
		System.out.print("Cuanto es su sueldo bruto?: ");
		double sueldoBruto = read.nextDouble();
		double totalDesc = Resultado.descSalud(sueldoBruto) + Resultado.descAFP(sueldoBruto);
		double totalSueldo = sueldoBruto - totalDesc;

		System.out.print("El Sueldo a Pagar es " );
		if(totalSueldo % 1 == 0){
			int resultInt = (int)totalSueldo;
			System.out.println(resultInt);
		} else {
			System.out.println(totalSueldo);
		}
	}
}
