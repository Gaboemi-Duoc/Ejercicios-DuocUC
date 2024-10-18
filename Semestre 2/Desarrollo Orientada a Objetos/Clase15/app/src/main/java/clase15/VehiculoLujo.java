package main.java.clase15;

public class VehiculoLujo extends Vehiculo implements CostoAlquilerCalculable {
    
    public VehiculoLujo(String idVehiculo, String modelo, double costoBaseAlquiler) {
        super(idVehiculo, modelo, costoBaseAlquiler);
    }

    @Override
    public double calcularCostoAlquiler() {
        return costoBaseAlquiler * 1.21;
    }
}
