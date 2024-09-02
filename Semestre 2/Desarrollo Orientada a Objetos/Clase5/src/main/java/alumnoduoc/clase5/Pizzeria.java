
package alumnoduoc.clase5;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Gabo's
 */
public class Pizzeria {

	public static Scanner read = new Scanner(System.in).useLocale(Locale.US);
	public static void main(String[] args) {
		System.out.println("Welcome to Freddy Fazbear's Pizzeria!");

		Menu.menu();
		read.close();
	}
}
