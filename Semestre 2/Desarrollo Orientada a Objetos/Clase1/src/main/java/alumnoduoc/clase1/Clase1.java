/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.clase1;
import java.util.Scanner;

/**
 * @author Gabo's
 */

public class Clase1 {

	public static void main(String[] args) {
		System.out.println("Funciona!");
//        String name = "Gabo J";
//        int age = 22;
//        double alt = 1.72;
//        boolean is_married = false;
//        boolean is_older;
//        
//        
//        System.out.print("Mi nombre es " + name + ", y tengo " + age + " años");
//        System.out.println(", ademas tengo " + alt + " Metros de Altura!");
//        
//        
//        if(is_married) {
//            System.out.println("Esta Condenado...");
//        }else{
//            System.out.println("Esta \'Libre\'");
//        }
//      
//        if(age < 18){
//            System.out.println("Soy Menor de Edad");
//        }else if(age < 65) {
//            System.out.println("Soy un Adulto");
//        }else {
//            System.out.println("Soy un Adulto Mayor (Abuelo)");
//        }
//
//        is_older = (age >= 18);
//        System.out.println(is_older);
//        
//        int day = 5;
//        switch(day) {
//            case 1:
//                System.out.println("Monday");
//                break;
//            case 2:
//                System.out.println("Tuesday");
//                break;
//            case 3:
//                System.out.println("Wednesday");
//                break;
//            case 4:
//                System.out.println("Thursday");
//                break;
//            case 5:
//                System.out.println("Friday");
//                break;
//            case 6:
//                System.out.println("Saturday");
//                break;
//            case 7:
//                System.out.println("Sunday");
//                break;
//        }

		
//        for(int i=1; i<5; i++){
//            System.out.println("N = " + i);
//        }
//        for(int i=5; i>0; i--){
//            System.out.println("N = " + i);
//        }

//        for(int i = 1; i <= 10; i++ ){
//            System.out.println("Tabla del " + i);
//            for(int e = 1; e <= 10; e++){
//                System.out.println(e + " x " + i + " = " + e*i);
//            }
//        }
		
//        String[] cars = {"Volvo","Ford"};
//        for (int i = 0; i < cars.length; i++){
//            System.out.println(cars[i]);
//        }
//        for (String car : cars) {
//            System.out.println(car);
//        }

//        String[] nombres = {"Pedro", "Juan", "Diego"};
//        String[] apellidos = {"Rojas", "Naranjo", "Cortez"};
//        for (int i = 0; i < nombres.length; i++){
//            System.out.println("Alumno "+ nombres[i] + " " + apellidos[i]);
//        }
		
		Scanner leer = new Scanner(System.in);
		
//        System.out.print("Ingrese su Nombre: ");
//        String name = leer.next();
//        System.out.println("Su Nombre tiene " + name.length() + " Caracteres...");
//        int age = leer.nextInt();
//        
//        if(age < 18){
//            System.out.println("Usted NO Puede entrar a la Pagina");
//        }else {
//            System.out.println("Usted Puede entrar a la Pagina");
//        }
		
//        int intentos = 0;
//        String passUser = "pass123";
//        boolean ingreso = false;
//        while(intentos < 3){
//            System.out.print("Ingrese contraseña: ");
//            String passInput = leer.next();
//            if(passInput.equals(passUser)){
//                ingreso = true;
//                break;
//            }
//            else{
//                System.out.println("Incorrecto, vuelva a intentarlo...");
//            }
//            intentos++;
//        }
//        if(ingreso){
//            System.out.println("Usted Puede entrar a la Pagina");
//        }else{
//            System.out.println("Usted NO Puede entrar a la Pagina");
//        }
		
		int option = 0;
		while(option != 5) {
			System.out.println("1. Revisar si es Mayor de Edad");
			System.out.println("2. Contar los Numeros de Caracteres en su Nombre");
			System.out.println("3. Obtener la Tabla de N Numero");
			System.out.println("4. Algo");
			System.out.println("5. Salir");
			System.out.print("Ingrese una Opcion: ");
			option = leer.nextInt();
			switch (option) {
				case 1:
					System.out.print("Ingrese su Edad: ");
					int age = leer.nextInt();
					if(age < 18){
						System.out.println("Usted NO Puede entrar a la Pagina");
					}else {
						System.out.println("Usted Puede entrar a la Pagina");
					}
					break;
				case 2:
					System.out.print("Ingrese su Nombre: ");
					String name = leer.next();
					System.out.println("Su Nombre tiene " + name.length() + " Caracteres...");
					break;
				case 3:
					System.out.print("Ingrese el Numero que desee tener la Tabla: ");
					int num = leer.nextInt();
					for(int e = 1; e <= 10; e++){
						System.out.println(e + " x " + num + " = " + e*num);
					}
					break;
				case 4:
					System.out.println("Algo dijo usted?");
					System.out.print("> ");
					leer.next();
					System.out.println("Mmmmmh Definitivamente dijo Algo");
					System.out.println("Continue con su Vida...");
					break;
				case 5:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opcion Invalida...");
			}
		}
		
		leer.close();
	}
}
