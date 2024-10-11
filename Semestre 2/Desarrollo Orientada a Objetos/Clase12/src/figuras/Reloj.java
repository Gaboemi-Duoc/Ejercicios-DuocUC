package figuras;

// Los relojes deben ser capaces de dibujarse
public class Reloj implements Dibujable {

    private String marca;

    public Reloj(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public void dibujar() {
        System.out.println("Reloj " + marca + ": @");
    }

    @Override
    public String toString() {
        return "Reloj{" + "marca=" + marca + '}';
    }
}
