from os import system
system("cls")

edad = -1
while edad < 0 or edad > 95:
	try:
		edad = int(input("Ingrese su edad: "))
		if edad < 0 or edad > 95:
			print("Error: Fuera de Rango")
	except:
		print("Error: Ingrese un numero valido!")

