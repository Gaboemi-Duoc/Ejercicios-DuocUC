from os import system
system("cls")

# 3.1.3 act 3

arreglo = []

first = 1
last = 4
for i in range(10):
	arreglo.append([*range(first,last), last]) # *range(first,last) se descomprime(? para poner en el arreglo
	first = last + 1
	last = first + 3


for y in arreglo:
	for x in y:
		if y.index(x) == 2:
			print("\t",end="")
		elif x > 9:
			print(f"  ",end="")
		else:
			print(f"   ",end="")
		print(f"{x}", end="")
	print()