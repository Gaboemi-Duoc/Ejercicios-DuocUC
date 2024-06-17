from os import system
system("cls")

#ingrese n numero en pantalla, n siendo numero dado por ustedes del 1 al 10
#solicitar n numero
#contar los pares y los impares

countImpar = 0
countPar = 0

n = int(input("Ingrese la cantidad de numeros a ingresar: "))
for i in range(n):
	num = int(input("Ingrese un numero: "))

	if num % 2 == 0:
		countPar += 1
	else:
		countImpar += 1

print(f"Hay {countPar} pares y {countImpar} impares ingresados...")

