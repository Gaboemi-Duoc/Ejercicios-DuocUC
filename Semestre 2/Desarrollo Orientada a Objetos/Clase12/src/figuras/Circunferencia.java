package figuras;
               
public class Circunferencia extends FiguraGeometrica {

    private String nombre;
    private int radio;

    public Circunferencia(String nombre, int radio) {
        super(nombre); // Se invoca al constructor de la superclase
        this.nombre = nombre;
        this.radio = radio;
    }

    @Override
    public void dibujar() {
        System.out.println(super.getNombre() + ": ●");
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public String toString() {
        return "Circunferencia{" + "nombre=" + nombre + ", radio=" + radio + '}';
    }
}
