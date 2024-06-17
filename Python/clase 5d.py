from os import system
system("cls")

# Ciclo while -> menu y validar
# Usar while para supermercado

total = 0

print("Bienvenido al Supermercado Costo Cero!")

opcion = -1
while opcion != 0:

	print(f"1. Arroz\t$1490 \n2. Fideos\t$900 \n3. Atun \t$1120 \n4. Carne\t$3200")
	try: opcion = int(input("Elija un Producto, ingrese 0 para salir: "))
	except: pass


	if opcion == 0:
		system("cls")

	elif opcion in range(1,5):
		if opcion == 1:
			pago = 1490
		elif opcion == 2:
			pago = 900
		elif opcion == 3:
			pago = 1120
		elif opcion == 4:
			pago = 3200

		try: cantidad = int(input("Cuantos lleva?: "))
		except: cantidad = 0

		total += cantidad * pago
		system("cls")
		print(f"Valor de su carrito de Compra: ${total}")

	else:
		system("cls")
		print(f"Opcion invalida, porfavor ingrese otro valor...")


if total > 0:
	opcionPago = 0
	while opcionPago not in range(1,4):
		try: opcionPago = int(input("Opciones de pago:\n1. Efectivo\t10% de Descuento \n2. Debito\t3% de descuento\n3. Credito\t4% de Impuestos \nComo desea pagar?: "))
		except: pass
		
		system("cls")
		if opcionPago == 1:
			total *= 1-0.1
		elif opcionPago == 2:
			total *= 1-0.03
		elif opcionPago == 3:
			total *= 1.04
		else:
			print("Opcion invalida, porfavor ingrese otro valor...")
			
	total = int(round(total))
	print(f"La cantidad a pagar es de ${total}")
	print("Gracias por su Compra! :>")

else:
	print("Gracias por visitar al Supermercado Costo Cero, regrese pronto! :>")