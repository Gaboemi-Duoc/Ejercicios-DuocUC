
package alumnoduoc.gestionvehiculo;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class GestionVehiculo {

	public static void main(String[] args) {
		Sucursal bonitaSucursal = new Sucursal("0","Sucursal Santiago Centro");
		Vehiculo carrito1 = new VehiculoEstandar("AI01", "Nissan", 2000);
		Vehiculo carrito2 = new VehiculoEstandar("FR01", "Ford", 3000);
		Vehiculo carrito3 = new VehiculoEstandar("FR02", "Ford", 2900);
		Vehiculo carrote1 = new VehiculoLujo("LU01", "Ferrari", 10500);
		Vehiculo carrote2 = new VehiculoLujo("LU02", "Ferrari", 11500);
		
		bonitaSucursal.agregarVehiculo(carrito1);
		bonitaSucursal.agregarVehiculo(carrito2);
		bonitaSucursal.agregarVehiculo(carrito3);
		bonitaSucursal.agregarVehiculo(carrote1);
		bonitaSucursal.agregarVehiculo(carrote2);

		System.out.println("En la " + bonitaSucursal.nombre + " estan disponibles los siguientes Vehiculos:");
		for (Vehiculo car : bonitaSucursal.vehiculosDisponibles) {
			System.out.println(car  + " - Costo Alquiler $" + (int)car.calcularCostoAlquiler());
		}
		
		System.out.println("Para un valor potencial como Sucursal en $" + (int)bonitaSucursal.calcularCostoTotalAlquiler());
		System.out.println("El precio para comprar el " + carrito2 + " es de $" + (int)carrito2.calcularPrecioVenta());
		System.out.println("El precio para comprar el " + carrote1 + " es de $" + (int)carrote1.calcularPrecioVenta());
	}
}
