from os import system
system("cls")

### ejercicio 3 ###
n = 0
while n <= 0:
	n = int(input("Ingrese un Numero entero positivo: "))

	if n <= 0:
		print("Numero no valido, intentelo de nuevo...")

suma = int( (n * ( n + 1 ) ) / 2 )


print(f"La suma de todos los enteros del 1 hasta {n} es de {suma}")
