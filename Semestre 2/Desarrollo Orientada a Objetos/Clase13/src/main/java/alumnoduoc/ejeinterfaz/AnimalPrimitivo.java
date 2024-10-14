package alumnoduoc.ejeinterfaz;

public class AnimalPrimitivo {
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
	public String toString() {
		return "AnimalPrimitivo{" + "nombre=" + nombre + '}';
	}
    
}
