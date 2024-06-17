from os import system
system("cls")

# 3.1.2 act 1

nombres = []
n = e = 0

for i in range(3):
	name = input("Ingrese un Nombre: ")
	nombres.append(name)
	if len(name) > n:
		n = len(name)
		e = i

print(f"El nombre con más caracteres es {nombres[e]}, con {len(nombres[e])} caracteres...")