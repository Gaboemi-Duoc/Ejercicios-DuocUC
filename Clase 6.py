from os import system
system("cls")

## Act 2.5.3

deuda = 100000

opcion = -1
while opcion != 3:
	print(f"La Deuda de la Tarjeta es de ${deuda}")
	print("1. Pago de Tarjeta de Credito\n2. Simulacion de Compra\n3. Salir")

	try: opcion = int(input("Ingrese una opcion: "))
	except: opcion = -1

	system("cls")
	if opcion == 1:
		print(f"La Deuda de la Tarjeta es de ${deuda}")
		pago = -1
		while pago < 0:
			try: pago = int(input("Ingrese la cantidad del Pago: $"))
			except: pago = -1

			system("cls")
			if pago < 0:
				print("Cantidad Invalida! Porfavor ingrese un valor valido...")
				print(f"El Saldo de la Tarjeta es de ${deuda}")
			elif pago > deuda:
				print("Cantidad Excede la Deuda! Porfavor ingrese otro valor...")
				print(f"El Saldo de la Tarjeta es de ${deuda}")
				pago = -1
		deuda -= pago

	elif opcion == 2:
		totalCompras = 0
		try: nCompras = int(input("Ingrese la Cantidad de Compras a Realizar: "))
		except: nCompras = 0

		for i in range(5):
			compra = -1
			while compra < 0:
				print(f"Monto de Compra es de ${totalCompras}")
				try: compra = int(input("Ingrese el monto de la compra: $"))
				except: compra = -1
				
				system("cls")
				if compra < 0:
					print("Cantidad Invalida! Porfavor ingrese un valor valido...")
			totalCompras += compra
		
		deuda += totalCompras

	if opcion == 3:
		pass

	else:
		system("cls")
		print("Opcion invalida, porfavor ingrese otro valor...")
