from os import system
system("cls")

# 3.4.2 Ejercicio 1

def sumar(a, b):
	return a + b
def restar(a, b):
	return a - b
def multiplicar(a, b):
	return a * b
def dividir(a, b):
	if b == 0:
		print("Error! No se puede dividir por 0")
	else:
		return a / b
	
n1 = int(input("Ingrese un numero: "))
n2 = int(input("Ingrese otro numero: "))
print(f"Suma: {n1} + {n2} = {sumar(n1,n2)}")
print(f"Resta: {n1} - {n2} = {restar(n1,n2)}")
print(f"Multiplicacion: {n1} * {n2} = {multiplicar(n1,n2)}")
print(f"Division: {n1} / {n2} = {dividir(n1,n2)}")