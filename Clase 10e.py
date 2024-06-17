from os import system
system("cls")

# 3.1.2 act 5

productos = {
	1:{
		"nombre":"Jugo",
		"precio":1300
	},
	2:{
		"nombre":"Chocolate",
		"precio":2150
	},
	3:{
		"nombre":"Sandwich",
		"precio":3000
	},
	4:{
		"nombre":"Fideos",
		"precio":900
	},
	5:{
		"nombre":"Carne",
		"precio":3200
	}
}

canasta = []
total = 0

print("Bienvenido a {tienda_nombre}!")
opcion = -1
while opcion != 4:
	print("1. Agregar Producto \n2. Ver Canasta \n3. Ver Total \n4. Salir")
	try: opcion = int( input("Ingrese una Opcion: ") )
	except: opcion = -1
	
	system("cls")
	if opcion == 1:
		for id in productos:
			print(f"{id}. {productos[id]["nombre"]} \t${productos[id]["precio"]}")
		try: idProd = int( input("Elija un Producto, ingrese 0 para Cancelar: ") )
		except: idProd = -1

		system("cls")
		if idProd == 0:
			pass

		elif idProd in range(1,len(productos)+1):
			total += productos[idProd]["precio"]
			canasta.append(productos[idProd]["nombre"])
			print(f"{productos[idProd]["nombre"]} agregado a la Canasta!")

		else:
			print("Opcion invalida, porfavor ingrese otro valor...")

	elif opcion == 2:
		if len(canasta) != 0:
			for i in canasta:
				print(f"- {i}")

			input("Inserte Enter para Continuar...")
			system("cls")
			print(f"Hay {len(canasta)} Productos en la Canasta!")
		else:
			print("No hay nada en la Canasta!")
	elif opcion == 3:
		print(f"Valor de su Canasta: ${total}")
	elif opcion == 4:
		print(f"Total a Pagar: ${total}")
		print("Gracias por su Compra! :>")
	else:
		print("Opcion invalida, porfavor ingrese otro valor...")
