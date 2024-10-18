package main.java.clase15;

public class VehiculoEstandar extends Vehiculo implements CostoAlquilerCalculable {
    
    public VehiculoEstandar(String idVehiculo, String modelo, double costoBaseAlquiler) {
        super(idVehiculo, modelo, costoBaseAlquiler);
    }

    @Override
    public double calcularCostoAlquiler() {
        return costoBaseAlquiler;
    }
}
