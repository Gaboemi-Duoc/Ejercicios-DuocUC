
package alumnoduoc.herencia;

/**
 *
 * @author Gabo's
 */
public class Avion extends Vehiculo {
	int alturaMaxima, numPropulsores;

	public Avion(int alturaMaxima, int numPropulsores, int velocidadMax, String marca, String modelo) {
		super(marca, modelo, velocidadMax);
		this.alturaMaxima = alturaMaxima;
		this.numPropulsores = numPropulsores;
	}
	
	
}
