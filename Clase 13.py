from os import system
import csv
import json
system("cls")

# 3.3.3

archivoCSV = open('Clase 13 listadoRutEmpresa.csv', 'r')

csvData = csv.reader(archivoCSV)
####   Version ordenado por RUT   #### 
rawData = []
for line in csvData:
	if csvData.line_num == 1:
		keys = line
	else:
		rawData.append(line)

data = {}
for i in rawData:
	ventas = int(i[2])
	if ventas <= 100_000_000:
		classEmpresa = "Pequeño Contribuyente"
	elif ventas > 100_000_000 and ventas <= 200_000_000:
		classEmpresa = "Mediano Contribuyente"
	else:
		classEmpresa = "Gran Contribuyente"

	data.update({
		i[0]:{
			keys[1]: i[1],
			keys[2]: ventas,
			"clasificacionEmpresa": classEmpresa
	}})


####   Version Ordenado por clasificacionEmpresa   ####
# rawData = []
# for line in csvData:
# 	if csvData.line_num == 1:
# 		keys = line
# 		keys.append('clasificacionEmpresa')
# 	else:
# 		dato = line
# 		ventas = int(line[2])
# 		if ventas <= 100_000_000:
# 			classEmpresa = "Pequeño Contribuyente"
# 		elif ventas > 100_000_000 and ventas <= 200_000_000:
# 			classEmpresa = "Mediano Contribuyente"
# 		else:
# 			classEmpresa = "Gran Contribuyente"
# 		dato.append(classEmpresa)
# 		rawData.append(dato)

# data = {
# 	'Pequeño Contribuyente':{},
# 	'Mediano Contribuyente':{},
# 	'Gran Contribuyente':{}
# }
# for i in rawData:
# 	classEmpresa = i[3]
# 	data[classEmpresa].update({
# 		i[0]:{
# 			keys[1]: i[1],
# 			keys[2]: i[2]
# 		}
# 	})

try: archivoJSON = open('Clase 13 segmentacionEmpresas.json', 'x', newline='')
except: archivoJSON = open('Clase 13 segmentacionEmpresas.json', 'w', newline='')

json.dump(data, archivoJSON, indent=4)

# for i in data:
# 	print(i, end=" : \n")
# 	for e in data[i]:
# 		print(data[i][e])

archivoCSV.close()
archivoJSON.close()