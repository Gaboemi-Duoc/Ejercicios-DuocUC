

package alumnoduoc.herencia;

/**
 *
 * @author Gabo's
 */
public class Herencia {

	public static void main(String[] args) {
		Automobil autito = new Automobil(5, 150, "KIA", "Sportage");
		Motocicleta motito = new Motocicleta(150, 125, "Kawasaki", "Ninja");
		System.out.println(autito);
		System.out.println(motito);
	}
}
