package alumnoduoc.sistemagestion;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Departamento {
	String idDepartamento, nombre;
	ArrayList<Empleado> empleados;

	public Departamento(String idDepartamento, String nombre) {
		this.idDepartamento = idDepartamento;
		this.nombre = nombre;
		this.empleados = new ArrayList<Empleado>();
	}

	void agregarEmpleado(Empleado newEmpleado) {
		empleados.add(newEmpleado);
	}

	double calcularCostoTotalSalarios() {
		double totalCosto = 0;
		for (Empleado empleado : empleados) {
			totalCosto += empleado.calcularSalario();
		}
		return totalCosto;
	}
}
