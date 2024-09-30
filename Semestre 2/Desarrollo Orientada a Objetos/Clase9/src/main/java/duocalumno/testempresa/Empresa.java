/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duocalumno.testempresa;

import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class Empresa {
	ArrayList<Empleado> listaEmpleados;

	public Empresa() {
		listaEmpleados = new ArrayList<Empleado>();
	}

	public boolean agregarEmpleado(Empleado empl) {
		return listaEmpleados.add(empl);
	}

	public boolean buscarEmpleado(String rut) {
		for (Empleado empleado : listaEmpleados) {
			if (empleado.getRut().equals(rut)) {
				return true;
			}
		}
		return false;
	}

	public void mostrarEmpleados() {
		for (Empleado empleado : listaEmpleados) {
			System.out.println(empleado.toString());
		}
	}

	public boolean eliminarEmpleado(String rut) {
		for (Empleado empleado : listaEmpleados) {
			if (empleado.getRut().equals(rut)) {
				listaEmpleados.remove(empleado);
				return true;
			}
		}
		return false;
	}
}
