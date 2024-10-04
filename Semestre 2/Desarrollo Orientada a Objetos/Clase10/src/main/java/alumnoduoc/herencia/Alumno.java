/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.herencia;

/**
 *
 * @author Gabo's
 */
public class Alumno extends Ciudadano {
	int notaGeneral;

	public Alumno(int notaGeneral, String rut, String nombre, String apPaterno, String apMaterno) {
		super(rut, nombre, apPaterno, apMaterno);
		this.notaGeneral = notaGeneral;
	}
	
}
