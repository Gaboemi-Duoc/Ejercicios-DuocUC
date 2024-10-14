

package duocalumno.testempresa;

/**
 *
 * @author Gabo's
 */
public class TestEmpresa {

	public static void main(String[] args) {
		Puesto post1 = new Puesto(1, "gerente");
		Puesto post2 = new Puesto(2, "ejecutivo");

		Empleado empleado1 = new Empleado("1-9", "tomas", 'M', 10, 40, post1);
		Empleado empleado2 = new Empleado("2-7", "juana", 'F', 2,  25, post2);

		Empresa empresa = new Empresa();

		if (empresa.buscarEmpleado("1-9") == false) {
			empresa.agregarEmpleado(empleado1);
			System.out.println("Se agrego empleado " + empleado1.getName());
		} else {
			System.out.println("Empleado ya existe");
		}

		empresa.mostrarEmpleados();

		if (empresa.eliminarEmpleado("2-7")) {
			System.out.println("Se elimino empleado " + empleado2.getName());
		} else {
			System.out.println("No se elimino empleado " + empleado2.getName());
		}
	}
}
