from os import system
system("cls")

### ejercicio 4 ###
peso = float(input("Ingrese su peso: "))
estatura = float(input("Ingrese su altura: "))

imc = peso / estatura

print(f"Tu indice de masa corporal es de {imc}")
