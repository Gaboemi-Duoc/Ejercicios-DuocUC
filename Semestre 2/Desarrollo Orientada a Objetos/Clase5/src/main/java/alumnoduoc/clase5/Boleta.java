
package alumnoduoc.clase5;
import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Boleta {

	ArrayList<Pizza> boletaPizza;
	ArrayList<Palitos> boletaPalitos;
	ArrayList<Bebida> boletaBebida;
	int totalBoleta;

	public Boleta() {
		boletaPizza = new ArrayList<>();
		boletaPalitos = new ArrayList<>();
		boletaBebida = new ArrayList<>();
	}

	public void addPizza(Pizza pizza) {
		boletaPizza.add(pizza);
		totalBoleta += pizza.getPrice();
	}
	public void addPalitos(Palitos palitos) {
		boletaPalitos.add(palitos);
		totalBoleta += palitos.getPrice();
	}
	public void addBebida(Bebida bebida) {
		boletaBebida.add(bebida);
		totalBoleta += bebida.getPrice();
	}

	public void printBoleta() {
		for (Pizza pizza : boletaPizza) {
			System.out.println(". " + pizza.getName() + " " + pizza.getMass() + " " + pizza.getSize() + "\t - $" + pizza.getPrice() );
		}
		for (Palitos palito : boletaPalitos) {
			System.out.println(". " + palito.getType() + " " + palito.getSize() + "\t - $" + palito.getPrice() );
		}
		for (Bebida bebida : boletaBebida) {
			System.out.println(". " + bebida.getType() + " " + bebida.getSize() + "\t\t\t\t - $" + bebida.getPrice() );
		}
		System.out.println( "Total de la Boleta - $" + totalBoleta );
		System.out.println();
	}

}
