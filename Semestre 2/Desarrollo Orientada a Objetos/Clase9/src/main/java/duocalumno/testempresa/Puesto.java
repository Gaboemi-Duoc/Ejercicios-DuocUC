/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duocalumno.testempresa;

/**
 *
 * @author Gabo's
 */
public class Puesto {
	private int code;
	private String namePuesto;

	public Puesto() {}

	public Puesto(int code, String namePuesto) {
		this.code = code;
		this.namePuesto = namePuesto;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNamePuesto() {
		return namePuesto;
	}

	public void setNamePuesto(String namePuesto) {
		this.namePuesto = namePuesto;
	}

	@Override
	public String toString() {
		return "Puesto{" + "code=" + code + ", namePuesto=" + namePuesto + '}';
	}

	
}
