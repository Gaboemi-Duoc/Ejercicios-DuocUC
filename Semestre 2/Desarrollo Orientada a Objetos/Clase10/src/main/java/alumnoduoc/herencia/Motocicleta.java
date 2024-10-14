
package alumnoduoc.herencia;

/**
 *
 * @author Gabo's
 */
public class Motocicleta extends Vehiculo {
	int cc;

	public Motocicleta(int cc, int velocidadMax, String marca, String modelo) {
		super(marca, modelo, velocidadMax);
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "Motocicleta{" + "cc=" + cc + ", marca=" + marca + ", modelo=" + modelo + '}';
	}
	
	
}
