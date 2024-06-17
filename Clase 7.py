from os import system

system("cls")

# Crear un Menu con las siguientes operaciones:
# 1. Ingresar un nombre por pantalla el nombre debe tener entre 4 y 20 caracteres, una vez ingresado, se debe cambiar todos los caracteres "o" por "i"
# 2. Ingresar un monto por pantalla el cual debe ser mayor a 9 000 y menor a 100 000, luego aplicar el impuesto de valor agregado (19%)
# 3. Solicitar la altura de un triangulo y dibujar un triangulo como se muestra a continuacion:
# n= 3  *
#      ***
#     *****
# 4. Ingresando el dia y un mes de nacimiento, debe mostrar que signo del zodiaco corresponde y luego un mensaje personalizado para c/u

name = zodiaco = ""
montoTotal = option = -1
dibujarFin = False

while option != 0:
	if name != "": print(f"Hola {name}! :3")
	if zodiaco != "": print(f"Sos tremendo {zodiaco}!")
	if montoTotal != -1: print(f"Monto calculado en total es de ${montoTotal} a pagar.")
	if dibujarFin == True : print(f"Seguro que eres un buen artista...")
	print("1. Ingresar su Nombre\n"+
		  "2. Calcular Impuesto de Monto\n"+
		  "3. Dibujar un Triangulo\n"+
		  "4. Averigue su Signo del Zodiaco\n")
	try: option = int(input("Ingrese una opcion, 0 para Salir: "))
	except: option = -1

	system("cls")
	if option == 0: break
	if option == 1:
		name = ""
		while name == "":
			try: name = input("Ingrese su nombre: ")
			except: name = ""
			
			system("cls")
			if len(name) not in range(4,21):
				print("Error! Su nombre debe ser entre 4 y 20 Caracteres...")
				name = ""
			else:
				name = name.replace("o","i")
			
	elif option == 2:
		monto = -1
		while monto == -1:
			try: monto = int(input("Ingrese un monto mayor a 9 000 y menor a 100 000, para calcular impuesto: "))
			except: monto = -1

			system("cls")
			if monto <= 9000 and monto >= 100_000:
				print("Error! El monto tiene que ser un Numero y este debe ser menor a 9 000 y mayor a 100 000...")
				monto = -1
			else:
				impuesto = 1.19
				monto = monto * impuesto
				montoTotal += round(monto)
				input(f"Se le aplicara un impuesto del 19%, dando como monto ${round(monto)} \nPresione Enter para continuar... \n")
				system("cls")
				

	elif option == 3:
		dibujarFin = False
		while dibujarFin == False:
			try: h = int(input("Ingrese la altura del triangulo a dibujar: "))
			except: h = -1

			system("cls")
			if h <= 0:
				print("Error! Ingrese una altura valida....")
			else:
				draw = "*"
				for i in range(h):
					space = " " * (h-i+1)
					print(space+draw)
					draw = "*"+draw+"*"

				print("")
				input("Presione Enter cuando quiera dejar de presenciar esta obra de arte... \n")
				system("cls")
				dibujarFin = True

	elif option == 4:
		mes = 0
		while mes == 0:
			mes = input("Ingrese su mes de nacimiento: ")
			try:
				mes = int(mes)
			except:
				if type(mes) == str:
					mes = mes.lower()
					if mes == "enero": mes = 1
					elif mes == "febrero": mes = 2
					elif mes == "marzo": mes = 3
					elif mes == "abril": mes = 4
					elif mes == "mayo": mes = 5
					elif mes == "junio": mes = 6
					elif mes == "julio": mes = 7
					elif mes == "agosto": mes = 8
					elif mes == "septiembre": mes = 9
					elif mes == "octubre": mes = 10
					elif mes == "noviembre": mes = 11
					elif mes == "diciembre": mes = 12
					else: mes = 0

			system("cls")
			if mes in range(1,13):
				if mes == 1:
					mesNombre = "Enero"
				elif mes == 2:
					mesNombre = "Febrero"
				elif mes == 3:
					mesNombre = "Marzo"
				elif mes == 4:
					mesNombre = "Abril"
				elif mes == 5:
					mesNombre = "Mayo"
				elif mes == 6:
					mesNombre = "Junio"
				elif mes == 7:
					mesNombre = "Julio"
				elif mes == 8:
					mesNombre = "Agosto"
				elif mes == 9:
					mesNombre = "Septiembre"
				elif mes == 10:
					mesNombre = "Octubre"
				elif mes == 11:
					mesNombre = "Noviembre"
				elif mes == 12:
					mesNombre = "Diciembre"

				dia = 0
				while dia == 0:
					try: dia = int(input("Ingrese su dia de nacimiento: "))
					except: dia = 0

					system("cls")
					if dia > 0 and (mes in (1,3,5,7,8,10,12) and dia <= 31) or (mes in (4,6,9,11) and dia <= 30) or (mes == 2 and dia <= 29):
						if (mes == 1 and dia >= 21) or (mes == 2 and dia <= 19): # Acuario
							zodiaco = "Acuario"
							frase = "Eres una persona simpática, original y brillante, también eres muy humanitario, al mismo tiempo que independiente e intelectual."

						elif (mes == 2 and dia >= 20) or (mes == 3 and dia <= 20): # Piscis
							zodiaco = "Piscis"
							frase = "Eres una persona compleja. Sensible ante el sufrimiento de los demás, respondes con buena voluntad y ganas de ayudar."

						elif (mes == 3 and dia >= 21) or (mes == 4 and dia <= 19): # Aries
							zodiaco = "Aries"
							frase = "Eres una persona rebosante de energía y entusiasmo; avanzada y aventurera, adoras la libertad, los retos y las nuevas ideas."
							
						elif (mes == 4 and dia >= 20) or (mes == 5 and dia <= 20): # Tauro
							zodiaco = "Tauro"
							frase = "Eres una persona firme, decidida y constante en varios sentidos. Eres pragmático y tienes una enorme fuerza de voluntad."
							 
						elif (mes == 5 and dia >= 21) or (mes == 6 and dia <= 20): # Geminis
							zodiaco = "Geminis"
							frase = "Eres una persona inquieta, al que le gusta empezar nuevos proyectos y retos con mucho entusiasmo, aunque con demasiada frecuencia te falta la constancia necesaria para realizarlos."

						elif (mes == 6 and dia >= 21) or (mes == 7 and dia <= 22): # Cancer
							zodiaco = "Cancer"
							frase = "Eres una persona que tiene una memoria extraordinaria y además posees un talento innato para las artes y las letras notable."

						elif (mes == 7 and dia >= 23) or (mes == 8 and dia <= 22): # Leo
							zodiaco = "Leo"
							frase = "Eres una persona entusiasta, creativa y muchas veces comprensiva con las circunstancias de los demás; adoras los lujos y la aventura; correr riesgos te motiva."

						elif (mes == 8 and dia >= 23) or (mes == 9 and dia <= 22): # Virgo
							zodiaco = "Virgo"
							frase = "Eres una persona perfeccionista, caes en obsesiones de todo tipo: desde la obsesión por el orden, pasando por la limpieza, hasta llegar incluso a la obsesión por los detalles."

						elif (mes == 9 and dia >= 23) or (mes == 10 and dia <= 22): # Libra
							zodiaco = "Libra"
							frase = "Eres una persona que presenta un sentido de la ecuanimidad y la tolerancia muy desarrollado; comprendes las posturas de los demás y al mismo tiempo procuras dirimir las diferencias."

						elif (mes == 10 and dia >= 23) or (mes == 11 and dia <= 21): # Escorpio
							zodiaco = "Escorpio"
							frase = "Eres una persona que tiene mucha imaginación e intuición, además de una gran capacidad para el análisis, fuerza de voluntad y firmeza, aunque también eres muy sensible y emocional consigo mismo y con el entorno."

						elif (mes == 11 and dia >= 22) or (mes == 12 and dia <= 21): # Sagitario
							zodiaco = "Sagitario"
							frase = "Eres una persona intuitiva, organizadora, generosa (pero sin malgastar) y muy cuidadosa, lo que te convierte en buen gestor de situaciones y proyectos."

						elif (mes == 12 and dia >= 22) or (mes == 1 and dia <= 20): # Capricornio
							zodiaco = "Capricornio"
							frase = "Eres una persona trabajadora, responsable y dispuesta a persistir lo que haga falta para conseguir tus objetivos."

						print(f"Tu, nacido el {dia} de {mesNombre}, tu signo es: \n\n{zodiaco}\n\n{frase}\n\n")
						input("\nPresione Enter para continuar... ")
						system("cls")
					else:
						dia = 0
						print("Error! Dia ingresado no es valido...")
			else:
				mes = 0
				print("Error! Mes ingresado no es valido...")





	else: print("Error! Opcion invalida...")
