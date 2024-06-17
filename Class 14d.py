from os import system
system("cls")

# 3.4.3

def validarListaNumeros():
	while True:
		try:
			textLista = input("Ingrese una Lista de Numeros enteros separados por espacios: ")
			lst = textLista.split(" ")
			for i in range(len(lst)):
				lst[i] = int(lst[i])
			return lst
		except:
			print("Error! Ingrese un Numero Valido...")



listaNum = validarListaNumeros()

listaPar = []
listaImpar = []
for i in listaNum:
	if i % 2 == 0:
		listaPar.append(i)
	else:
		listaImpar.append(i)

print(f"Numeros Pares: {listaPar}")
print(f"Numeros Impares: {listaImpar}")