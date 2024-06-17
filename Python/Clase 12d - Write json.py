from os import system
import json
system("cls")

## Write JSON

datos = {
	"alumno1": {
		"nombre": "Esteban",
		"edad": 25,
		"comuna": "Santiago",
		"estudios": ["Colegio Arturo Prat", "Liceo El Bosque", "Duoc UC", "Diplomado Duoc UC"]
	},
	"alumno2": {
		"nombre": "Maria",
		"edad": 30,
		"comuna": "Osorno",
		"estudios": ["Duoc UC", "Diplomado Duoc UC"]
	}
	
}
escritura = open('nuevo_documentos.json', "w")
json.dump(datos,escritura)