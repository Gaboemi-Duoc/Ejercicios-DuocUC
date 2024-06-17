from os import system
system("cls")

#2.4.2 Ejercicio 1

nLivianos = 0
nNormales = 0
try: bultos = int(input("Ingrese la cantidad de Bultos: "))
except: bultos = -1

for i in range(bultos):
	try: peso = int(input(f"Ingrese el peso del Bulto N°{i+1}: "))
	except: peso = -1

	if peso > 0 and peso < 6:
		nLivianos += 1
	elif peso > 5 and peso < 11:
		nNormales += 1
	else:
		print("Peso no Valido! El valor tiene que ser entre 1 y 10 Kg, Saltando Bulto...")

if bultos > 0:
	valorLiviano = nLivianos*1000
	valorNormales = nNormales*2000
	valorTotal = valorLiviano + valorNormales
	if nLivianos == 1:
		print(f"{nLivianos} Bulto Liviano ${valorLiviano}")
	elif nLivianos > 1:
		print(f"{nLivianos} Bultos Livianos ${valorLiviano}")
	if nNormales == 1:
		print(f"{nNormales} Bulto Normal ${valorNormales}")
	elif nNormales > 1:
		print(f"{nNormales} Bultos Normales ${valorNormales}")
	
	print(f"Valor total a pagar: ${valorTotal}")
else:
	print("Cantidad de Bultos Invalidas, Saliendo del Programa...")