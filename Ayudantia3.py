from os import system
import json
import csv
system("cls")

# Solicitar el ingreso de datos de un paciente:
# Rut, Nombre, Fecha Nacimiento, Sexo, Dolencia
# Y generar un archivo pacientes.json

pacienteDict = {}
pacienteLst = []
validYes = ("si","yes","y","s")
validNo = ("no","n")
ingresar = "si"
while ingresar in validYes:
	paName = input("Ingrese el Nombre del Paciente: ")
	paRut = input("Ingrese el Rut del Paciente: ")
	paFechaNac = input("Ingrese la Fecha de Nacimiento del Paciente: ")
	paSex = input("Ingrese el Sexo del Paciente: ")
	paIll = input("Ingrese la Dolencia del Paciente: ")
	# Method 1, ordenarlos por Rut
	pacienteDict.update({
		paRut:{
			"nombre": paName,
			"fechaNac": paFechaNac,
			"sexo": paSex,
			"dolencia":paIll
		}
	})
	#Method 2, lista (el que fue pedido jsjsjs)
	pacienteLst.append(
		{
			"rut": paRut,
			"nombre": paName,
			"fechaNac": paFechaNac,
			"sexo": paSex,
			"dolencia":paIll
		}
	)
	ingresar = ""
	while not(ingresar in validYes or ingresar in validNo):
		ingresar = input("Desea ingresar otro paciente? [Si/No]: ").lower()
		if not(ingresar in validYes or ingresar in validNo):
			print("Opcion no valida, por favor ingrese una opcion valida!")

with open("Ayudantia3 pacientes.json","w+") as archivo:
	json.dump(pacienteDict,archivo,indent=4)

with open("Ayudantia3 pacientes.csv", "w+") as archivo:
	csvWriter = csv.writer(archivo)
	csvWriter.writerow(["Rut","Nombre","Fecha Nacimiento","Sexo","Dolencia"])
	for i in pacienteDict:
		pac = []
		pac.append(i)
		for e in pacienteDict[i]:
			pac.append(pacienteDict[i][e])
		csvWriter.writerow(pac)