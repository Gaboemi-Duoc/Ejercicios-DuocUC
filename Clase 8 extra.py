from os import system
system("cls")

alumnos = [ "Luis", "Maria", "Jose", "Carlos" ]

ongoingClass = True
while ongoingClass == True:
	print(alumnos)
	ingresado = input("Ingrese Alumno que ingrese, 0 si termina la clase: ")
	if ingresado != "0":
		alumnos.append(ingresado)
		
		print(f"{alumnos[-2]} dejo pasar al alumno {alumnos[-1]}" )
	else:
		ongoingClass = False

listaAlumnos = ""
for alumno in alumnos:
	listaAlumnos += "- "+alumno+"\n"

print(f"Fin de Clase! La lista es:\n{listaAlumnos}")

			