from os import system
system("cls")

# 3.1.2 act 2

# nombres = []
# apellidos = []

# for i in range(3):
# 	nombreLocal = input("Ingrese un nombre: ")
# 	apellidoLocal = input("Ingrese un apellido: ")
# 	nombres.append(nombreLocal)
# 	apellidos.append(apellidoLocal)

# for i in range(3):
# 	print(f"- {nombres[i]} {apellidos[i]}")

nombres = [ ]

for i in range(3):
	nombreLocal = input("Ingrese un nombre: ")
	apellidoLocal = input("Ingrese un apellido: ")
	nombres.append( (nombreLocal, apellidoLocal) )

nombres.sort()

for i in nombres:
	print(f"- {i[0]} {i[1]}")