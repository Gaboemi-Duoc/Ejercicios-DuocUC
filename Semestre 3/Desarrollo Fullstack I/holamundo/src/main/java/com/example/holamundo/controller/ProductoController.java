package com.example.holamundo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/product")
public class ProductoController {

	

	@GetMapping("/index")
	public String getIndex() {
		return "bienvenidos al listado de productos";
	}

	@GetMapping("/{idProducto}")
	public String buscar(@PathVariable int idProducto) {
		ArrayList<Integer> productList = new ArrayList<Integer>();
		productList.add(1);
		productList.add(2);
		productList.add(3);
		productList.add(4);
		productList.add(5);
		productList.add(6);
		productList.add(7);
		productList.add(8);
		productList.add(9);

		if (productList.contains(idProducto)) {
			return "producto encontrado";
		} else {
			return "error 404";
		}
	}
}
