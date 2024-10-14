
package alumnoduoc.ejeinterfaz;

/**
 *
 * @author Gabo's
 */

public class Gallina implements Animal {

	@Override
	public void hablar() {
		System.out.println("Pio, Pio");
	}

	@Override
	public void mover() {
		System.out.println("La Gallina se bambolea");
	}

	public void ponerHuevo() {
		System.out.println("La Gallina puso un Huevo");
	}
}
