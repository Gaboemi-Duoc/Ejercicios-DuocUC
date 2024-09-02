
package alumnoduoc.clase5;
import static alumnoduoc.clase5.Menu.nextInt;

/**
 *
 * @author Gabo's
 */
public class Bebida {
	
	private String type, size;
	private int price;

	public Bebida() {
		this.price = 0;

		this.setType();
		this.setSize();
	}

	// set'ers
	private void setType() {
		System.out.println("Eliga su Bebida:");
		System.out.println("1. Coca Cola");
		System.out.println("2. Sprite");
		System.out.print("Ingrese una Opcion: ");
		int option = nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.type = "Coca Cola";
				this.price += 1000;
				break;
		
			case 2:
				this.type = "Sprite";
				this.price += 800;
				break;
		
			default:
				setType();
				break;
		}
	}
	
	private void setSize() {
		System.out.println("Eliga un Tamaño");
		System.out.println("1. 250 ML");
		System.out.println("2. 500 ML");
		System.out.println("3. 1 L");
		System.out.print("Ingrese una Opcion: ");
		int option = nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.size = "0.25L";
				break;
		
			case 2:
				this.size = "0.5L";
				this.price *= 1.4;
				break;
		
			case 3:
				this.size = "1L";
				this.price *= 1.95;
				break;
		
			default:
				setSize();
				break;
		}
	}
	// get'ers
	public String getType() {
		return type;
	}
	public String getSize() {
		return size;
	}
	public int getPrice() {
		return price;
	}
}
