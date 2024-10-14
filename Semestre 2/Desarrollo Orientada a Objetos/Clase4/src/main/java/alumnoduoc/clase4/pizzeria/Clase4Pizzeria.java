

package alumnoduoc.clase4.pizzeria;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Gabo's
 */


public class Clase4Pizzeria {

	public static Scanner read = new Scanner(System.in).useLocale(Locale.US);
	public static void main(String[] args) {
		int clientID=0;
		
		System.out.println("Bienvenido a la Pizzeria de Luigi!");
		System.out.print("Ingrese su Nombre estimado Cliente: ");
		Client client = new Client(clientID, read.nextLine());
		clientID+=1;

		Pizza pizza1 = new Pizza("pepperoni", "mediano", "gruesa", "$5600");
		pizza1.setSize("extra grande");

		client.setOrderedPizza(pizza1);
		String name = client.getOrderedPizza().getName();
		String price = client.getOrderedPizza().getPrice();
		String size = client.getOrderedPizza().getSize();
		System.out.println("Estimado " + client.getNameClient() + ", usted ha comprado una pizza " + name + " de tamaño " + size);
		System.out.println("El precio de su pizza seria de " + price);

		read.close();
	}

}
