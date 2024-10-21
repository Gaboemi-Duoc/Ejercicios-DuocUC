
package alumnoduoc.gestionvehiculo;

/**
 *
 * @author Gabo's
 */
public abstract class Vehiculo implements CostoAlquilerCalculable {
	String idVehiculo, modelo;
	double costoBaseAlquiler;

	public Vehiculo(String idVehiculo, String modelo, double costoBaseAlquiler) {
		this.idVehiculo = idVehiculo;
		this.modelo = modelo;
		this.costoBaseAlquiler = costoBaseAlquiler;
	}

	public abstract double calcularCostoAlquiler();
	public double calcularPrecioVenta() {
		return 3.5 * costoBaseAlquiler;
	}

	@Override
	public String toString() {
		return idVehiculo + " " + modelo;
	}
}
