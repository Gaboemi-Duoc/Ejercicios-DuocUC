
package alumnoduoc.ejeinterfaz;

/**
 *
 * @author Gabo's
 */
public class Pato implements Animal {
	
	@Override
	public void hablar() {
		System.out.println("Cuak, Cuak");
	}

	@Override
	public void mover() {
		System.out.println("El Pato nada");
	}
	
}
