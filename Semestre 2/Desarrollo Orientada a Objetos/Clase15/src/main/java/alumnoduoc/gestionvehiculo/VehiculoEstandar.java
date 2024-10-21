
package alumnoduoc.gestionvehiculo;

/**
 *
 * @author Gabo's
 */
public class VehiculoEstandar extends Vehiculo {
	
	public VehiculoEstandar(String idVehiculo, String modelo, double costoBaseAlquiler) {
		super(idVehiculo, modelo, costoBaseAlquiler);
	}

	@Override
	public double calcularCostoAlquiler() {
		return costoBaseAlquiler;
	}
}
