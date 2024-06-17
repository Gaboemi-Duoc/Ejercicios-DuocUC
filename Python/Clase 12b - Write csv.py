from os import system
import csv
system("cls")

## Write CSV

try: archivoCSV = open('nuevo_archivo.csv', 'x', newline='')
except: archivoCSV = open('nuevo_archivo.csv', 'w', newline='')
escritorArchivo = csv.writer(archivoCSV)

escritorArchivo.writerow(['Nombre', 'Edad', 'Comuna'])

escritorArchivo.writerows([
	['Esteban', 25, 'Santiago'],
	['Maria', 30, 'Valparaiso'],
	['Carlos', 22, 'Osorno'],
	['Sigrid', 25, 'Santiago'],
	['Daniela', 30, 'La Cisterna'],
	['Aylen', 22, 'La florida']
])

archivoCSV.close()