/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.clase6;

/**
 *
 * @author Gabo's
 */
public class Exam {
	String name;
	int code, value;

	public Exam(String name, int code, int value) {
		this.name = name;
		this.code = code;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public int getCode() {
		return code;
	}
	public int getValue() {
		return value;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Exam{" + "name=" + name + ", code=" + code + ", value=" + value + '}';
	}
	
	
}
