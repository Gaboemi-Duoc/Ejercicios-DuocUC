from os import system
system("cls")

# 3.1.3 act 1

arreglo = [
	[3, 10, 4, 16],
	[1,  7, 8, -7],
	[9, -1, 3,  9]
]

for y in arreglo:
	for x in y:
		print(f"{x}", end="\t")
	print()