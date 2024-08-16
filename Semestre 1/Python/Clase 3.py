from os import system
system("cls")

### Actividad ###
# Ingresar Nombre
# Ingresar Estatura
# Ingresar Peso
# Sacar peso / estatura^2
# Mostrar calculo por pantalla

name = input("Ingrese su Nombre: ")
height = float( input("Ingrese su Estatura en Metros: ") )
weight = float( input("Ingrese su Peso en Kg: ") )

imc = weight / (height * height)

print(f"Resultado de IMC de {name} es {imc}")
if imc > 40:
	print("Su IMC es de Obesidad Grado 3")
elif imc >= 35:
	print("Su IMC es de Obesidad Grado 2")
elif imc >= 30:
	print("Su IMC es de Obesidad Grado 1")
elif imc >= 25:
	print("Su IMC es de Sobrepeso")
elif imc >= 18.5:
	print("Su IMC es Adecuado!")
else:
	print("Su IMC es de Bajo Peso")