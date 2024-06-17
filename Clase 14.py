from os import system
system("cls")

# 3.4.1

# 1.
def resta(a,b):
	return a - b
print(f"1. resta = {resta(30,10)}")

# 2.
print(f"2. resta = {resta(b=30,a=10)}")

# 3.
def funcion():
	return "Welcome to Python! Yipii!!"

frase = funcion()
print(f"3. {frase}")

# 4.
def resta(a=None,b=None):
	if a == None or b == None:
		print("Error! Faltan Parametros a la Funcion resta(a,b)")
		return
	else: return a - b

print("4. ", end="")
resta()

# 5.
def calculo(precio, descuento):
	return precio - (precio * descuento / 100)

datos = [10000,10]
print(f"5. El monto final a pagar es {calculo(*datos)}")

# 6.
def saludo(nombre, mensaje="Python"):
	print(mensaje, nombre)

print("6. ", end="")
saludo(mensaje="Buen dia", nombre="Pedro")
