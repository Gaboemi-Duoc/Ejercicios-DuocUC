package main.java.clase15;
import java.util.ArrayList;

public class Sucursal {
    String idSucursal, nombre;
    ArrayList<Vehiculo> vehiculosDisponibles;

    public Sucursal(String idSucursal, String nombre) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        vehiculosDisponibles = new ArrayList<Vehiculo>();
    }

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
