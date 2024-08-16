from os import system
system("cls")

# Actividad: Solicitar nombre, edad y estatura por pantalla y mostrar la informacion
name = input("Ingrese su nombre: ")

edad = int(input ("Ingrese edad: "))

estatura = float(input("Ingrese su estatura: "))

if edad >= 18:
    print("Es mayor de edad!")
else:
    print("No es mayor de edad...")
    
print("El nombre del usuario es", name + ", su edad es de", edad, "y su estatura es de", estatura, "metros!")