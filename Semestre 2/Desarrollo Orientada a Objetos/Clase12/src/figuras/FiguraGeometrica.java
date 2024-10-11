package figuras;

// Las figuras geométricas deben ser capaces de dibujarse
public abstract class FiguraGeometrica implements Dibujable {

    private String nombre;

    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract double perimetro();
    public abstract double area();
}
