/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.clase5;
import static alumnoduoc.clase5.Pizzeria.read;

/**
 *
 * @author Gabo's
 */
public class Pizza {
	
	private String name, size, mass;
	private int price;

	public Pizza() {
		this.price = 0;
		
		this.setName();
		this.setMass();
		this.setSize();
	}

	// set'ers
	private void setName() {
		System.out.println("Eliga su Pizza:");
		System.out.println("1. Super Pepperoni");
		System.out.println("2. Cuatro estaciones");
		System.out.println("3. Pizza de Pepperoni");
		System.out.println("4. Pizza Cuatro Quesos");
		System.out.print("Ingrese una Opcion: ");
		int option = read.nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.name = "Super Pepperoni";
				this.price += 5500;
				break;
		
			case 2:
				this.name = "Cuatro estaciones";
				this.price += 5900;
				break;
		
			case 3:
				this.name = "Pizza de Pepperoni";
				this.price += 5000;
				break;
		
			case 4:
				this.name = "Pizza Cuatro Quesos";
				this.price += 4650;
				break;
		
			default:
				setName();
				break;
		}
	}
	
	private void setSize() {
		System.out.println("Eliga un Tamaño");
		System.out.println("1. Chica");
		System.out.println("2. Mediana");
		System.out.println("3. Familiar");
		System.out.print("Ingrese una Opcion: ");
		int option = read.nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.size = "Chica";
				break;
		
			case 2:
				this.size = "Mediana";
				this.price *= 1.2;
				break;
		
			case 3:
				this.size = "Familiar";
				this.price *= 1.7;
				break;
		
			default:
				setSize();
				break;
		}
	}
	
	private void setMass() {
		System.out.println("Eliga una Masa");
		System.out.println("1. Chicago ");
		System.out.println("2. Carbonara");
		System.out.println("3. Napolitana");
		System.out.print("Ingrese una Opcion: ");
		int option = read.nextInt();
		System.out.println("");
		switch (option) {
			case 1:
				this.mass = "Chicago";
				break;
		
			case 2:
				this.mass = "Carbonara";
				this.price *= 1.05;
				break;
		
			case 3:
				this.mass = "Napolitana";
				this.price *= 1.1;
				break;
		
			default:
				setMass();
				break;
		}
	}

	// get'ers
	public String getName() {
		return name;
	}
	public String getSize() {
		return size;
	}
	public String getMass() {
		return mass;
	}
	public int getPrice() {
		return price;
	}
	
}
