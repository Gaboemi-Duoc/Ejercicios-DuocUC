package com.example.holamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolaMundoController {

	@GetMapping("/hola")
	public String holaMundoString() {
		return "Hola Mundo!";
	}

	@GetMapping("/adios")
	public String adiosString() {
		return "Adiós Mundo Cruel...";
	}

	@GetMapping("/hello/{name}")
	public String holaPedro(@PathVariable String name) {
		return "Hola a todos, mi nombre es " + name;
	}

	// Operaciones
	@GetMapping("/suma/{valor1}/{valor2}")
	public int suma(@PathVariable int valor1, @PathVariable int valor2) {
		return valor1 + valor2;
	}

	@GetMapping("/resta/{valor1}/{valor2}")
	public int resta(@PathVariable int valor1, @PathVariable int valor2) {
		return valor1 - valor2;
	}

	@GetMapping("/multiplicacion/{valor1}/{valor2}")
	public int multiplicacion(@PathVariable int valor1, @PathVariable int valor2) {
		return valor1 * valor2;
	}

	@GetMapping("/division/{valor1}/{valor2}")
	public int division(@PathVariable int valor1, @PathVariable int valor2) {
		return valor1 / valor2;
	}
}