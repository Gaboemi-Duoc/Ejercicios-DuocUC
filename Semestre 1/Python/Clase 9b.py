from os import system
system("cls")

miLista = []

option = -1
while option != 4:
	print("1. Agregar un elemento a la lista \n2. Eliminar un elemento a la lista \n3. Mostrar la lista \n4. Salir")

	try: option = int(input("Ingrese una opcion: "))
	except: option = -1

	system("cls")
	if option == 1:
		try: miLista.append(input("Ingresa el nuevo elemento: "))
		except: print("Error! Elemento no valido...")

	elif option == 2:
		try: miLista.remove(input("Ingresa el elemento a eliminar: "))
		except: print("Error! Elemento no en la lista...")
		
	elif option == 3:
		print(miLista)

	elif option == 4:
		pass
	else: print("Error! Opcion no valida...")