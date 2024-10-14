
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

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Client{" + "code=" + code + ", nombre=" + nombre + ", salud=" + salud + '}';
	}
	
	
}
