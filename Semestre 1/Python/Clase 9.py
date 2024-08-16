from os import system
system("cls")

# Colecciones Homogeneas y Heterogeneos
# Homogeneas: mismo tipo de datos, datos primitivos
# Heterogeneos: distinto tipo de datos, datos primitivos y datos estrucuturados

# Datos Primitivos son byte, int, float, char y bool ---- tipos no vistos ---> double, bale, short, long
# Datos Estructurados son str, array, list, tuple, sets, dictionary

nombreLista = ['a','b','c']
print(len(nombreLista))

for i in nombreLista:
	print(i, end="")

print()

milista = [1,2,3,4,5]
milista.append(6)
for elemento in milista:
	print(elemento)

milista.insert(3,"Juan")
milista.remove("Juan")
milista.reverse()
milista.pop()
print(milista)