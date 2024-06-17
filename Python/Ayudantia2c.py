from os import system
system("cls")

#2.4.2 Ejercicio 3

try:
    
	numerador = int(input("Ingrese el Numerador: "))
	divisor = int(input("Ingrese el Divisor: "))
	if divisor == 0:
		raise ValueError("El divisor no puede ser cero.")
	resultado = numerador / divisor
	print(f"El Resultado de esta Divison es: {resultado}")

except ZeroDivisionError as er:
	print("Error: No se puede dividir por 0")
except ValueError as x:
	print(f"Error: {x}")
