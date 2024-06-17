from os import system
system("cls")

# ingresar un nombre por pantalla, luego contar la cantidad de vocales que tiene ese nombre
nombre = input("Ingrese Nombre: ")

n = 0
nA = 0
nE = 0
nI = 0
nO = 0
nU = 0

for i in nombre:
	if i in "aeiou":
		n += 1
		if i == "a":
			nA += 1
		elif i == "e":
			nE += 1
		elif i == "i":
			nI += 1
		elif i == "o":
			nO += 1
		elif i == "u":
			nU += 1

if n > 0:
	if n == 1:
		if nA > 0:
			vocal = "A"
		elif nE > 0:
			vocal = "E"
		elif nI > 0:
			vocal = "I"
		elif nO > 0:
			vocal = "O"
		elif nU > 0:
			vocal = "U"
		final = f"{nombre} tiene una vocal, esta siendo {vocal}"
	else:
		vocalesA = ""
		vocalesE = ""
		vocalesI = ""
		vocalesO = ""
		vocalesU = ""
		if nA > 0:
			vocalesA = f", con {nA} A"
		if nE > 0:
			vocalesE = f", con {nE} E"
		if nI > 0:
			vocalesI = f", con {nI} I"
		if nO > 0:
			vocalesO = f", con {nO} O"
		if nU > 0:
			vocalesU = f", con {nU} U"

		
		final = f"{nombre} tiene {n} vocales{vocalesA}{vocalesE}{vocalesI}{vocalesO}{vocalesU}..."
		if final.count(",") > 1:
			final = final[::-1].replace(",","y ",1)[::-1]

	print(final)
else:
	print(f"{nombre} no tiene vocales?!")
	
