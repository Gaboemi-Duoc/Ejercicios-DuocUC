from os import system
system("cls")

### ejercicio 2 ###

horaTrabajadas = float( input("Ingrese la cantidad de horas trabajadas: ") )
horaValor = int( input("Ingrese el valor de cada hora: ") )

pago = horaTrabajadas * horaValor
pago = int(pago)

print(f"El pago que le corresponde al trbajador es de ${pago}")
