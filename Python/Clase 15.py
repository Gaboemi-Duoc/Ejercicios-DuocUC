from os import system
from json import dump, load
from csv import reader, writer
system("cls")

# Venta de Entradas de Cine
# 64 Asientos, 8 Filas y 8 Columnas
# Se requiere almacenar el Nombre, Edad, Telefono
# Cree Funciones para:
# Reservar Asiento
# Cancelar Reserva
# Ver Asientos Disponibles
# Una ves terminado, guardar la informacion en json y cargar a repositorio

# Def de Funciones
def reservaAsiento(nAsiento):
	if nAsiento % 8 == 0:
		x = 7
		y = nAsiento // 8 - 1
	else:
		x = nAsiento % 8 - 1
		y = nAsiento // 8
	print(x,y)
	nombre = "name"
	asientosDisponibles.remove(nAsiento)
def cancelarReservaAsiento():
	pass
def verAsientos(compra = False):
	print('''  n = Asiento Disponible
  X = Asiento Reservado
	   ''')
	for y in asientosCine:
		print('  ', end='')
		for x in y:
			if type(x) != type(tuple()):
				print(x, end=' ')
				if len(str(x)) == 1:
					print(' ', end='')
			else:
				print('X', end='  ')
		print()

	print()
	if compra == False:
		input("Aprete Enter para Continuar ")
		system("cls")


# Creacion de asientos
asientosDisponibles = {i for i in range(1,65)}
asientosCine = [i for i in range(8)]
it = 0
for i in asientosCine:
	asientosCine[i] = [i for i in [i for i in range(1,65)][it:8+it]]
	it += 8

# Test
asientosCine[0][0] = ("nombre","21","+56")
asientosDisponibles.remove(1)

print("Bienvenido a VentaTicket.cl!")
opcion = -1
while opcion != 4:
	print("1. Reservar un Asiento \n2. Cancelar Reserva \n3. Ver Asientos Disponibles \n4. Salir")
	try: opcion = int( input("► ") )
	except: opcion = -1

	system("cls")
	if opcion == 1:
		nAsiento = int(input("Que Numero de Asiento desea?: "))
		reservaAsiento(nAsiento)
		# verAsientos(compra=True)
		# try: asiento = int(input("Que Numero de Asiento desea?: "))
		# except: asiento = 0

		# if asiento in asientosDisponibles:
		# 	reservaAsiento(asiento)
		# else:
		# 	system("cls")
		# 	print("Asiento No Disponible!")
	elif opcion == 2:
		cancelarReservaAsiento()
	elif opcion == 3:
		verAsientos()
	elif opcion == 4:
		pass
	else:
		print(f"Opcion invalida, porfavor ingrese otro valor...")
