/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.clase5;
import static alumnoduoc.clase5.Pizzeria.read;

/**
 *
 * @author pvespucio
 */
public class Palitos {
	
	private String type, size;
	private int price;

	public Palitos() {
		this.price = 0;

		this.setType();
		this.setSize();
	}

	// set'ers
	private void setType() {
		System.out.println("Eliga su Palito de Pizza:");
		System.out.println("1. Napolitana");
		System.out.println("2. Full Queso");
		System.out.print("Ingrese una Opcion: ");
		int option = read.nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.type = "Palitos Napolitana";
				this.price += 1500;
				break;
		
			case 2:
				this.type = "Palitos Full Queso";
				this.price += 1250;
				break;
		
			default:
				setType();
				break;
		}
	}
	
	private void setSize() {
		System.out.println("Eliga un Tamaño");
		System.out.println("1. Porcion Single");
		System.out.println("2. Porcion Par");
		System.out.println("3. Porcion Familiar");
		System.out.print("Ingrese una Opcion: ");
		int option = read.nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.size = "Porcion Single";
				break;
		
			case 2:
				this.size = "Porcion Par";
				this.price *= 1.8;
				break;
		
			case 3:
				this.size = "Porcion Familiar";
				this.price *= 3.4;
				break;
		
			default:
				setSize();
				break;
		}
	}
	
	// get'ers
	public String getType() {
		return type;
	}
	public String getSize() {
		return size;
	}
	public int getPrice() {
		return price;
	}
}
