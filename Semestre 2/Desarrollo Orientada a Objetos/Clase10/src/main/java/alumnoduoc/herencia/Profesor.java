
package alumnoduoc.herencia;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Profesor extends Ciudadano {
	int salario;
	ArrayList<Alumno> alumnosProfesor;

	public Profesor(int salario, ArrayList<Alumno> alumnosProfesor, String rut, String nombre, String apPaterno, String apMaterno) {
		super(rut, nombre, apPaterno, apMaterno);
		this.salario = salario;
		this.alumnosProfesor = alumnosProfesor;
	}

	
}
