/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumnoduoc.clase4.pizzeria;

/**
 *
 * @author Gabo's
 */
public class Pizza {
	
	private String name, size, mass, price;
	
	public void prepare() {
		System.out.println("Se esta Preparando la Pizza...");
	}

	public void heatUp() {
		System.out.println("Se esta Horneando su Pizza...");
	}

	public Pizza(String name, String size, String mass, String price) {
		this.name = name;
		this.size = size;
		this.mass = mass;
		this.price = price;
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
	public String getPrice() {
		return price;
	}

	// set'ers
	public void setName(String name) {
		this.name = name;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setMass(String mass) {
		this.mass = mass;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	
}
