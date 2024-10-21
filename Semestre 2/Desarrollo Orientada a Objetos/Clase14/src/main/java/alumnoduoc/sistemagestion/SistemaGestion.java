
package alumnoduoc.sistemagestion;

/**
 *
 * @author Gabo's
 */
public class SistemaGestion {
	public static void main(String[] args) {
		
		Departamento depHR = new Departamento("0", "Recursos Humanos");
		Departamento depSales = new Departamento("1", "Ventas");
		Departamento depIT = new Departamento("2", "IT");

		Empleado emp1 = new EmpleadoAsalariado("HR01", "Harry Osborn", 6000.);
		Empleado emp2 = new EmpleadoAsalariado("HR02", "Peter Parker", 5300.);
		
		depHR.agregarEmpleado(emp1);
		depHR.agregarEmpleado(emp2);

		Empleado emp3 = new EmpleadoPorHora("SL01", "Fred", 2300.);
		Empleado emp4 = new EmpleadoAsalariado("SL02", "Jeb", 1200.);

		depSales.agregarEmpleado(emp3);
		depSales.agregarEmpleado(emp4);

		System.out.println("Costos por departamento:");
		System.out.println(depHR.nombre);
		System.out.println("$" + (int)depHR.calcularCostoTotalSalarios());
		System.out.println(depSales.nombre);
		System.out.println("$" + (int)depSales.calcularCostoTotalSalarios());
		
		System.out.println("Costo Empresa: $" + (int)(depHR.calcularCostoTotalSalarios()+depSales.calcularCostoTotalSalarios()) );
	}
}
