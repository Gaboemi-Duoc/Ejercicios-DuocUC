from os import system
system("cls")

# 3.1.2 act 4

usuarios = [("Invitado",""), ("Generic","password")]

opcion = -1
user = 0
print(f"Bienvenido {usuarios[user][0]}! :>")
while opcion != 4:
	print("1. Inicio de Sesion \n2. Registro de Usuario \n3. Eliminar Usuario \n4. Salir")
	try: opcion = int( input("Ingrese una Opcion: ") )
	except: opcion = -1

	system("cls")
	if opcion == 1:
		for i in range(len(usuarios)):
			print(f"{i}. {usuarios[i][0]}")

		try: opcion2 = int(input("Seleccione un Usuario a Ingresar, ingrese -1 para cancelar:  "))
		except: opcion2 = -1

		
		if opcion2 < 0:
			system("cls")
		else:
			try:
				if usuarios[opcion2][0] != "Invitado":
					contra = input(f"Ingrese la contraseña del Usuario {usuarios[opcion2][0]}: ")
					
					system("cls")
					if contra == usuarios[opcion2][1]:
						user = opcion2
						print(f"Bienvenido {usuarios[user][0]}! :>")
					else:
						print("Error! Contraseña no Valida...")
				else:
					user = opcion2
					system("cls")
					print(f"Bienvenido {usuarios[user][0]}! :>")
			except:
				system("cls")
				print("Error! Usuario no Valido...")


	elif opcion == 2:

		newUser = input("Ingrese el nombre de usuario nuevo: ")

		alreadyExists = False
		for i in usuarios:
			if newUser == i[0]:
				alreadyExists = True
		
		if alreadyExists:
			system("cls")
			print("Error! El Usuario ya Existe...")
		else:
			newPass = input(f"Ingrese la nueva contraseña para {newUser}: ")

			usuarios.append((newUser,newPass))
			system("cls")
			print("Usuario Creado con Exito!")
		
	elif opcion == 3:
		for i in range(len(usuarios)):
			print(f"{i}. {usuarios[i][0]}")

		try: opcion2 = int(input("Seleccione un Usuario a Eliminar, ingrese -1 para cancelar:  "))
		except: opcion2 = -1

		if opcion2 < 0:
			system("cls")
		else:
			try:
				if usuarios[opcion2][0] != "Invitado":
					contra = input(f"Ingrese la contraseña del Usuario {usuarios[opcion2][0]}: ")

					if contra == usuarios[opcion2][1]:
						usuarios.pop(opcion2)
						system("cls")
						print("Usuario Eliminado con Exito...")
					else:
						system("cls")
						print("Error! Contraseña no Valida...")
				else:
					system("cls")
					print("El Usuario Invitado no se puede Eliminar!")
			except:
				system("cls")
				print("Error! Usuario no Valido...")

		

	elif opcion == 4:
		pass
	else: print(f"Opcion invalida, porfavor ingrese otro valor...")

print(usuarios)