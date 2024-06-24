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
def validar_num(textoNum):
	while True:
		try:
			n = int(input(textoNum))
			return n
		except:
			print("Error! Ingrese un Numero Valido...")
def validar_Str(textoStr):
	while True:
		try:
			n = input(textoStr)
			return n
		except:
			print("Error...")

def reservaAsiento(nAsiento):
	if nAsiento % 8 == 0:
		x = 7
		y = nAsiento // 8 - 1
	else:
		x = nAsiento % 8 - 1
		y = nAsiento // 8
	# print(x,y)

	nombre = validar_Str("Ingrese su Nombre: ")
	edad = validar_num("Ingrese su Edad: ")
	telefono = validar_Str("Ingrese su Numero de Telefono: ")

	asientosCine[y][x] = { nAsiento: {'nombre':nombre, 'edad':edad, 'telefono':telefono} }
	asientosDisponibles.remove(nAsiento)

def cancelarReservaAsiento():
	
	asientosMostrar = [i for i in range(8)]
	it = 0
	for i in asientosMostrar:
		asientosMostrar[i] = [i for i in [i for i in range(1,65)][it:8+it]]
		for e in range(len(asientosMostrar[i])):
			if asientosMostrar[i][e] in asientosDisponibles:
				asientosMostrar[i][e] = "X"
		it += 8

	nAsiento = -112458525	
	while nAsiento < 0 or nAsiento > 64 or nAsiento in asientosDisponibles:


		print('''  n = Asiento Reservado
		''')
		for y in asientosMostrar:
			print('  ', end='')
			for x in y:
				print(x, end=' ')
				if len(str(x)) == 1:
					print(' ', end='')
			print()

		print()
		if nAsiento in asientosDisponibles and nAsiento != -112458525 and not ( nAsiento  < 0 or nAsiento > 64 ):
			print("Ese asiento no tiene reserva...")
		elif ( nAsiento  < 0 or nAsiento > 64 ) and nAsiento != -112458525:
			print("Error! Asiento no es valido")
		try: nAsiento = int(input("Que Numero de Asiento quiere cancelar?, 0 para cancelar: "))
		except: nAsiento = -1
		system("cls")
	if nAsiento != 0:
		asientosDisponibles.add(nAsiento)
		
		if nAsiento % 8 == 0:
			x = 7
			y = nAsiento // 8 - 1
		else:
			x = nAsiento % 8 - 1
			y = nAsiento // 8
		
		asientosCine[y][x] = nAsiento

def verAsientos(compra = False):
	print('''  n = Asiento Disponible
  X = Asiento Reservado
	   ''')
	for y in asientosCine:
		print('  ', end='')
		for x in y:
			if type(x) != type(dict()):
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
asientosCine[0][0] = { 1: {'nombre':"John Dow",'edad':21,'telefono':"+56 9 1111 1111"} }
asientosDisponibles.remove(1)

print("Bienvenido a VentaTicket.cl!")
opcion = -1
while opcion != 4:
	print("1. Reservar un Asiento \n2. Cancelar Reserva \n3. Ver Asientos Disponibles \n4. Salir")
	try: opcion = int( input("► ") )
	except: opcion = -1

	system("cls")
	if opcion == 1:
		nAsiento = -112458525
		while nAsiento < 0 or nAsiento > 64 or nAsiento not in asientosDisponibles and not nAsiento == 0:
			verAsientos(compra=True)
			if nAsiento not in asientosDisponibles and nAsiento != -112458525 and not ( nAsiento  < 0 or nAsiento > 64 ):
				print("Ese asiento no esta disponible...")
			elif ( nAsiento  < 0 or nAsiento > 64 ) and nAsiento != -112458525:
				print("Error! Asiento no es valido")
			try: nAsiento = int(input("Que Numero de Asiento desea?, 0 para cancelar: "))
			except: nAsiento = -1
			system("cls")
		if nAsiento != 0:
			reservaAsiento(nAsiento)
	elif opcion == 2:
		cancelarReservaAsiento()
	elif opcion == 3:
		verAsientos()
	elif opcion == 4:
		try: archivoJSON = open('Clase 15 asientosCine.json', 'w+', newline='')
		except: archivoJSON = open('Clase 15 asientosCine.json', 'w', newline='')

		data = {}
		for i in asientosCine:
			for e in i:
				if type(e) == type(dict()):
					data.update(e)
		dump(data, archivoJSON, indent=4)

	else:
		print(f"Opcion invalida, porfavor ingrese otro valor...")
