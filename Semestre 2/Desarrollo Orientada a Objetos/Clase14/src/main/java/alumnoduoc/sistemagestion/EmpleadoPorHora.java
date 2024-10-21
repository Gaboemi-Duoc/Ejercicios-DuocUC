package alumnoduoc.sistemagestion;

public class EmpleadoPorHora extends Empleado {

	public EmpleadoPorHora(String idEmpleado, String nombre, Double sueldoBase) {
		super(nombre, nombre, sueldoBase);
	}

	@Override
	public double calcularBonus() {
		return calcularSalario() * 0.15;
	}
}
