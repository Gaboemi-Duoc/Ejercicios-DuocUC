package main.java.clase15;
import java.util.ArrayList;

public class Sucursal {
    String idSucursal, nombre;
    ArrayList<Vehiculo> vehiculosDisponibles;

    public void agregarVehiculo(Vehiculo newVehiculo) {
        vehiculosDisponibles.add(newVehiculo);
    }

    public double calcularCostoTotalAlquiler() {
        double totalAlquiler = 0;
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            totalAlquiler += vehiculo.calcularCostoAlquiler();
        }
        return totalAlquiler;
    }
}
