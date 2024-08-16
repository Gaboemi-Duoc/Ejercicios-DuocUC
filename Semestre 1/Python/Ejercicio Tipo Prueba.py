from os import system
system('cls')

def registrarTrabajador():
	nombre = input("Ingrese el Nombre y Apellido del Trabajador: ")
	system('cls')
	cargo = validarCargo()
	sueldo = -1
	while sueldo < 1:
		try: sueldo = int(input("Ingrese el Sueldo Bruto del Trabajador: "))
		except: sueldo = -1
		system('cls')
		if sueldo < 1:
			print("Error! Sueldo no es valido...")
	desSalud = round(sueldo * 0.07)
	desAFP = round(sueldo * 0.12)
	liquido = sueldo - desAFP - desSalud
	trabajador = (nombre,cargo,sueldo,desSalud,desAFP,liquido)
	trabajadores.append(trabajador)

def listarTrabajador():
	print('Nombre		Cargo	S.Bruto	D. Salud D. AFP	Liquido a Pagar')
	for t in trabajadores:
		for data in t:
			print(data, end="\t")
		print()
	input("Presione Enter para continuar... ")
	system('cls')

def imprimirSueldos():
	archivo = open('Ejercicio - Planilla de Sueldos.txt', "w+")
	archivo.write('Nombre, Cargo, Sueldo Bruto, Desc. Salud, Desc. Afp, Liquido a Pagar\n')
	for i in trabajadores:
		dato = ""
		for e in i:
			dato += str(e) + ", "
		dato = dato[:-2]
		dato += "\n"
		archivo.write(dato)
	
def validarCargo():
	while True:
		try:
			print("Cargos Validos: ")
			print(universoCargos)
			n = input("Ingrese el Cargo del Trabajador: ")
			system('cls')
			if n in universoCargos:
				return n
			else:
				print("Error! Cargo ingresado no es valido...")
		except:
			print("Error! Cargo ingresado no es valido...")

universoCargos = {'CEO','Desarrollador','Analista de Datos','QA','Marketing','Recursos Humanos'}

#                Nombre            Cargo  S.Bruto  D.Salud D. Afp  Liquido Pagar
trabajadores = [('Homero Simpson', 'CEO', 1000000, 70000,  120000, 810000)]

option = -1
while option != 4:
	print("1. Registrar Trabajador \n2. Listar Todos los Trabajadores \n3. Imprimir Planilla de Sueldos \n4. Salir del Programa")
	try: option = int(input("► "))
	except: option = -1

	system('cls')
	if option == 1:
		registrarTrabajador()
	elif option == 2:
		listarTrabajador()
	elif option == 3:
		imprimirSueldos()
	elif option == 4:
		pass
	else:
		print("Error! Opcion ingresada no es valida...")