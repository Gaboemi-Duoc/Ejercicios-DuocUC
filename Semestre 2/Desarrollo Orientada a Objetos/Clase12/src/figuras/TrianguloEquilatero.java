package figuras;
             
public class TrianguloEquilatero extends FiguraGeometrica {

    private int lado;

    public TrianguloEquilatero(String nombre, int lado) {
        super(nombre); // Se invoca al constructor de la superclase
        this.lado = lado;
    }

    @Override
    public void dibujar() {
        System.out.println(super.getNombre() + ": ▲");
    }

    @Override
    public double perimetro() {
        return lado * 3;
    }

    @Override
    public double area() {
        return (Math.sqrt(3) / 4) * Math.pow(lado, 2);
    }

    @Override
    public String toString() {
        return "TrianguloEquilatero{nombre=" + super.getNombre() + ", lado=" + lado + '}';
    }
}
