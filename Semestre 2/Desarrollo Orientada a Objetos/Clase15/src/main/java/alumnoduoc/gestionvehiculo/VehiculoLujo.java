
package alumnoduoc.gestionvehiculo;

/**
 *
 * @author Gabo's
 */
public class VehiculoLujo extends Vehiculo {
	
	public VehiculoLujo(String idVehiculo, String modelo, double costoBaseAlquiler) {
		super(idVehiculo, modelo, costoBaseAlquiler);
	}

	@Override
	public double calcularCostoAlquiler() {
		return costoBaseAlquiler * 1.21;
	}
	@Override
	public double calcularPrecioVenta() {
		return 9.5 * costoBaseAlquiler;
	}
}
