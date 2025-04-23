package com.example.holamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClienteController {

	@GetMapping("/index")
	public String getIndex() {
		return "bienvenidos al listado de clientes";
	}
}
