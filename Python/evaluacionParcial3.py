from csv import writer, reader
from evaluacion3Funciones import validarINT, mostrarEstacionamiento, reservarEstacionamiento, anularEstacionamiento, calcularIngresos, mostrarNReservas

estacionamiento = [[0,0,0,0,0,0,0,0,0,0,0,0], #1 precio $6000
				   [0,0,0,0,0,0,0,0,0,0,0,0], #2
				   [0,0,0,0,0,0,0,0,0,0,0,0], #3 precio $1500
				   [0,0,0,0,0,0,0,0,0,0,0,0], #4
				   [0,0,0,0,0,0,0,0,0,0,0,0], #5
				   [0,0,0,0,0,0,0,0,0,0,0,0]] #6

try:
	csvFile = open('Evaluacion Parcial 3 - Estacionamiento.csv', mode='r', newline='')
	csv = reader(csvFile)
	datosCSV = []
	for e in csv:
		datosCSV.append(e)
	datosCSV = datosCSV[1:]
	print(datosCSV)
	for i in datosCSV:
		fila = int(i[3]) - 1
		columna = int(i[4]) - 1
		estacionamiento[fila][columna] = (i[0],i[1])

except:
	csvFile = open('Evaluacion Parcial 3 - Estacionamiento.csv', mode='w+', newline='')
	csv = writer(csvFile)
	csv.writerow(['Nombre','Matricula','Ganancia Espacio','Fila','Columna'])
	csvFile.close()


opc = -1
while opc != 6:
	print()
	if opc == -1:
		print('Bienvenido a Estacionamientos DuocUC!')
	print('1. Mostrar Estado del Estacionamiento \n2. Reservar un Espacio \n3. Anular una Reserva \n4. Calcular Ingresos del Dia \n5. Mostrar el Numero de Espacios Reservados \n6. Salir')
	opc = validarINT(1,6)

	if opc == 1:
		mostrarEstacionamiento(estacionamiento)
	elif opc == 2:
		reservarEstacionamiento(estacionamiento)
	elif opc == 3:
		anularEstacionamiento(estacionamiento)
	elif opc == 4:
		calcularIngresos(estacionamiento)
	elif opc == 5:
		mostrarNReservas(estacionamiento)
	elif opc == 6:
		datos = []
		fila = 1
		columna = 1
		ingresos = 0
		for i in estacionamiento:
			for e in i:
				if type(e) == type(int()):
					pass
				else:
					data = []
					for x in e:
						data.append(x)
					if fila > 2:
						ingresos = 1500
					else: ingresos = 6000
					data.append(ingresos)
					data.append(fila)
					data.append(columna)
					datos.append(data)

				columna += 1
			fila += 1
			columna = 1


		csvFile = open('Evaluacion Parcial 3 - Estacionamiento.csv', mode='w+', newline='')
		csv = writer(csvFile)
		
		csv.writerow(['Nombre','Matricula','Ganancia Espacio','Fila','Columna'])
		csv.writerows(datos)
		csvFile.close()
		
		
		

		
