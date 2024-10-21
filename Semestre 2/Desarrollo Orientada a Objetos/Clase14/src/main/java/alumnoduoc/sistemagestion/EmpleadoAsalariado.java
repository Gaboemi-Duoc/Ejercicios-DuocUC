package alumnoduoc.sistemagestion;

public class EmpleadoAsalariado extends Empleado {

	public EmpleadoAsalariado(String idEmpleado, String nombre, Double sueldoBase) {
		super(nombre, nombre, sueldoBase);
	}

	@Override
	public double calcularBonus() {
		return calcularSalario() * 0.23;
	}
}
