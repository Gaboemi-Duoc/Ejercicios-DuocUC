
package alumnoduoc.sistemagestion;

/**
 *
 * @author Gabo's
 */
public abstract class Empleado {
	String idEmpleado, nombre;
	Double sueldoBase;

	// public Empleado(String idEmpleado, String nombre, Double sueldoBase) {
	// 	this.idEmpleado = idEmpleado;
	// 	this.nombre = nombre;
	// 	this.sueldoBase = sueldoBase;
	// }

	Double calcularSalario() {
		return sueldoBase;
	}
}
