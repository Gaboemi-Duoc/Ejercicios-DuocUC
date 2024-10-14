
package alumnoduoc.clase4.pizzeria;

/**
 *
 * @author Gabo's
 */
public class Client {
	private int numClient;
	private String nameClient;
	private Pizza orderedPizza;

	public Client(int numClient, String nameClient) {
		this.numClient = numClient;
		this.nameClient = nameClient;
	}

	public void orderPizza() {
		
	}

	// get'er
	public int getNumClient() {
		return numClient;
	}
	public String getNameClient() {
		return nameClient;
	}
	public Pizza getOrderedPizza() {
		return orderedPizza;
	}

	// set'er
	public void setOrderedPizza(Pizza orderedPizza) {
		this.orderedPizza = orderedPizza;
	}
	
}
