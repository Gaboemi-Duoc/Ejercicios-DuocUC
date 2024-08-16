from os import system
import time
from MainFunClass import Map

# Juego sin terminar basicamente

## Functions
def combat():
	print("Combat!")

def playerTurn():
	print("Turn!")

def playerActions():
	print("Action!")

def boolYesNo(text):
	while True:
		try:
			response = input(text).lower()
			if response in validYes:
				return True
			elif response in validNo:
				return False
			else:
				print("Opcion no valida, por favor ingrese una opcion valida!")
		except:
			print("Opcion no valida, por favor ingrese una opcion valida!")
	

validYes = ("si","yes","y","s")
validNo = ("no","n")
system("cls")

### START
firstTimer = boolYesNo("Bienvenido a <inserte nombre juego aqui>!\nEs su primera vez jugando? [Y/N]\n► ")
if firstTimer == True:
	lenMap = 12
	dificulty = 0
	print("Se seleccionaran valores recomendados:")
	print(f"Tamaño Mapa: {lenMap}\nDificultad: {dificulty}")
	time.sleep(3)
else:
	
	pass


system("cls")
name = input("Ingrese su nombre: \n► ")
system("cls")


height = 3
width = 5
arena = []
for x in range(width):
	arena.append([])
	for y in range(height):
		arena[x].append(0)

classSelect = 0
while classSelect == 0:
	classSelect = input("1. Guerrero \n2. Mago \n3. Explorador \nSeleccione una Clase:\n► ")
	
	try:
		classSelect = int(classSelect)
	except:
		if type(classSelect) == str:
			classSelect = classSelect.lower()
			if classSelect == "guerrero":
				classSelect = 1
			elif classSelect == "mago":
				classSelect = 2
			elif classSelect == "explorador":
				classSelect = 3
			else:
				classSelect = 0

	
	if classSelect == 1:
		maxHP = 125
		atk = 6
		className = "Guerrero"
	elif classSelect == 2:
		maxHP = 80
		atk = 4
		className = "Mago"
	elif classSelect == 3:
		maxHP = 100
		atk = 4
		className = "Explorador"
	else:
		print("Seleccion invalida, porfavor intentelo de nuevo...")
		classSelect = 0


hp = maxHP
coin = 10
alive = True

print(f"Seleccionado: {className}")
print("Estadistica inicial:\n"+
	  f"HP \t - {hp}\n"+
	  f"Monedas\t - ${coin}")

currMap = Map(lenMap)


alive = True
while alive:

	hp = 0
	if hp <= 0:
		alive = False

