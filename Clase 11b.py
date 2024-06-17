from os import system
system("cls")

# 3.1.3 act 2

arreglo = [
	[
		["Amarillo", "Amarillo", "Rojo"], ["Rojo", "Verde", "Blanco"], ["Amarillo", "Rojo", "Naranja"]
	],
	[
		["Verde", "Rojo", "Rojo"], ["Amarillo", "Naranja", "Rojo"], ["Verde", "Verde", "Naranja"]
	],
	[
		["Verde", "Amarillo", "Verde"], ["Amarillo", "Rojo", "Naranja"], ["Blanco", "Blanco", "Rojo"]
	]
]

amarillo = rojo = verde = naranja = blanco = 0
for x in arreglo:
	for y in x:
		for z in y:
			
			elemento = z.lower()
			
			if elemento == "amarillo":
				amarillo += 1
			elif elemento == "rojo":
				rojo += 1
			elif elemento == "naranja":
				naranja += 1
			elif elemento == "verde":
				verde += 1
			elif elemento == "blanco":
				blanco += 1

print(f"- Numero de elementos 'Amarillo': {amarillo}")
print(f"- Numero de elementos 'Rojo': {rojo}")
print(f"- Numero de elementos 'Naranja': {naranja}")
print(f"- Numero de elementos 'Verde': {verde}")
print(f"- Numero de elementos 'Blanco': {blanco}")