

package alumnoduoc.clase3;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Gabo's
 */
public class Clase3 {

	public static Scanner read = new Scanner(System.in).useLocale(Locale.US); // IMPORTANTE, que tira error un 1.2f por diferente Syntax; Espera un , otherwise
	public static void main(String[] args) {
		Menu.menu();
		read.close();
	}
}
