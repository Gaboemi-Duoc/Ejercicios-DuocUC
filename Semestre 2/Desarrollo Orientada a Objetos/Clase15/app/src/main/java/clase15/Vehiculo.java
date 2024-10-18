package main.java.clase15;

public abstract class Vehiculo {
    String idVehiculo, modelo;
    double costoBaseAlquiler;

    public Vehiculo(String idVehiculo, String modelo, double costoBaseAlquiler) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.costoBaseAlquiler = costoBaseAlquiler;
    }

    public abstract double calcularCostoAlquiler();
}
