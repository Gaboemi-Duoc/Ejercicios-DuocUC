from os import system
system("cls")

# 3.1.2 act 3

nombres = []

no = ""
nombres.append(input("Ingrese un Nombre: "))
while no != "no":
	nombreLocal = input("Ingrese un Nombre: ")
	if len(nombreLocal) < len(nombres[-1]):
		nombres.append(nombreLocal)
	else:
		nombres.insert(0,nombreLocal)

	no = input("Quiere seguir ingresando nombres? Ingrese 'No': ").lower()

nombres.pop(-1)

print(nombres)