/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duocalumno.testempresa;

/**
 *
 * @author Gabo's
 */
public class Empleado {
	private Puesto position;
	private String name, rut;
	private int acno, edad;
	private char gender;

	public Empleado() {
		this.position = new Puesto();
	}

	public Empleado(String rut, String name, char gender, int acno, int edad, Puesto position) {
		this.position = position;
		this.name = name;
		this.rut = rut;
		this.acno = acno;
		this.edad = edad;
		this.gender = gender;
	}

	public Puesto getPosition() {
		return position;
	}

	public void setPosition(Puesto position) {
		this.position = position;
	}

	public String getName() {
		return name.substring(0,1).toUpperCase() + name.substring(1);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getAcno() {
		return acno;
	}

	public void setAcno(int acno) {
		this.acno = acno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Empleado{" + "position=" + position + ", name=" + name + ", rut=" + rut + ", acno=" + acno + ", edad=" + edad + ", gender=" + gender + '}';
	}
	
	
}
