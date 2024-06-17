from os import system
system("cls")

total = 0
precio = [1300, 2150, 3000]
# precio1 = 1300
# precio2 = 2150
# precio3 = 3000

opcion = -1
while opcion != 0:
	print(f"1- Jugo\t\t${precio[0]} \n2- Chocolate\t${precio[1]} \n3- Sandwich\t${precio[2]}")
	opcion = int( input("Ingrese una opcion, presione 0 para salir: ") )

	system("cls")

	if opcion == 0:
		if total > 0:
			print(f"La cantidad a pagar es de {total}")
			pago = int(input("Cuanto cancela?: "))
			system("cls")

			while pago < total:
				print(f"Le falta ${total - pago}, porfavor ingrese más!")
				pago += int(input("Ingrese dinero: "))
				system("cls")

			if pago > total:
				print(f"Su vuelto es de ${pago - total}")
			print("Gracias por su Compra! :>")
		else:
			print("Visitenos de nuevo! :>")

	elif opcion == 1 or opcion == 2 or opcion == 3:
		total += precio[opcion-1]
		print(f"Valor de su carrito de Compra: ${total}")

	# elif opcion == 1:
	# 	total += precio1
	# 	print(f"Valor de su carrito de Compra: ${total}")
	# elif opcion == 2:
	# 	total += precio2
	# 	print(f"Valor de su carrito de Compra: ${total}")
	# elif opcion == 3:
	# 	total += precio3
	# 	print(f"Valor de su carrito de Compra: ${total}")

	else:
		print(f"Opcion invalida, porfavor ingrese otro valor...")
	


