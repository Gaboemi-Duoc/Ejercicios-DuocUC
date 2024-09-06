/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.clase6;

/**
 *
 * @author Gabo's
 */
public class Client {
	int code;
	String nombre, salud;

	public Client(int code, String nombre, String salud) {
		this.code = code;
		this.nombre = nombre;
		this.salud = salud;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Client{" + "code=" + code + ", nombre=" + nombre + ", salud=" + salud + '}';
	}
	
	
}
