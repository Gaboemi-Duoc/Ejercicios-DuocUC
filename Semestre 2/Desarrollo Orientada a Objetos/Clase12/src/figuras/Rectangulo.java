package figuras;
              
public class Rectangulo extends FiguraGeometrica {

    private int base;
    private int altura;

    public Rectangulo(String nombre, int base, int altura) {
        super(nombre); // Se invoca al constructor de la superclase
        this.base = base;
        this.altura = altura;
    }

    @Override
    public void dibujar() {
        System.out.println(super.getNombre() + ": █");
    }

    @Override
    public double perimetro() {
        return base * 2 + altura * 2;
    }

    @Override
    public double area() {
        return base * altura;
    }

    @Override
    public String toString() {
        return "Rectangulo{nombre=" + super.getNombre() + ", base=" + base + ", altura=" + altura + '}';
    }
}
