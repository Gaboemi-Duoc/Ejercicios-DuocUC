
package alumnoduoc.ejeinterfaz;

/**
 *
 * @author Gabo's
 */
public class Perro extends AnimalPrimitivo implements Animal {
	String raza;

	public Perro(String nombre, String raza) {
		this.nombre = nombre;
		this.raza = raza;
	}

	
	@Override
	public void hablar() {
		System.out.println("Woof, Woof");
	}

	@Override
	public void mover() {
		System.out.println("El Perro corre");
	}
	
	public String toString() {
		return "Perro{" + "nombre=" + nombre + ", raza=" + raza + '}';
	}
}
