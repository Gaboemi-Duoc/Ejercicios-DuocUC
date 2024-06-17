from os import system
system("cls")

nombre = input("Ingrese Nombre: ")

count = 0
for i in nombre:
    if i in "aeiou":
        count += 1

print(f"{nombre} tiene {count} vocales...")