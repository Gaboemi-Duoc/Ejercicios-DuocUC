from os import system
system("cls")

total = 0
pagoPorcentaje = [1-0.1, 1-0.03, 1.04] #0:-10%, 1:-3%, 2:4%
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
	},
	6:{
		"nombre":"Atun",
		"precio":1120
	}
}

print("Bienvenido a {tienda_nombre}!")
opcion = -1
while opcion != 0:
	for id in productos:
		print(f"{id}. {productos[id]["nombre"]} \t${productos[id]["precio"]}")
	try: opcion = int( input("Elija un Producto, ingrese 0 para salir: ") )
	except: opcion = -1

	

	if opcion == 0:
		system("cls")

	elif opcion in range(1,len(productos)+1):
		try: cantidad = int(input("Cuantos lleva?: "))
		except: cantidad = 0

		total += cantidad * productos[opcion]["precio"]
		system("cls")
		print(f"Valor de su carrito de Compra: ${total}")

	else:
		system("cls")
		print(f"Opcion invalida, porfavor ingrese otro valor...")
	

if total > 0:
	print(f"Valor de su carrito de Compra: ${total}")
	opcionPago = 0
	while opcionPago not in range(1,4):
		try: opcionPago = int(input("Opciones de pago:\n1. Efectivo\t10% de Descuento \n2. Debito\t3% de descuento\n3. Credito\t4% de Impuestos \nComo desea pagar?: "))
		except: pass
		
		system("cls")
		if opcionPago not in range(1,4):
			print("Opcion invalida, porfavor ingrese otro valor...")
		else:
			total *= pagoPorcentaje[opcionPago-1]
			total = int(round(total))
			
	print(f"La cantidad a pagar es de ${total}")
	try: pago = int(input("Cuanto cancela?: "))
	except: pago = 0
	system("cls")

	while pago < total:
		print(f"Le falta ${total - pago}, porfavor ingrese más!")
		try: pago += int(input("Ingrese dinero: "))
		except: pago = 0
		system("cls")

	if pago > total:
		print(f"Su vuelto es de ${pago - total}")
	print("Gracias por su Compra! :>")
else:
	print("Gracias por visitar {tienda_nombre}, regrese pronto! :>")