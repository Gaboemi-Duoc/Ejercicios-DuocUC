

package alumnoduoc.ejeinterfaz;

/**
 *
 * @author Gabo's
 */
public class EjeInterfaz {

	public static void main(String[] args) {
		System.out.println("Ejemplo de Interfaz:\n");
		Animal perro = new Perro("Marty", "Labrador");
		Animal pato = new Pato();
		Animal gallina = new Gallina();

		Animal[] aniLista = {perro, pato, gallina};

		for (Animal animal : aniLista) {
			animal.hablar();
			animal.mover();
			if (animal instanceof Gallina) {
				((Gallina)animal).ponerHuevo();
			}
			System.out.println();
		}
	}
}
