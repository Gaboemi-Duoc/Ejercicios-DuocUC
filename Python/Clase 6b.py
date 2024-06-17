from os import system
system("cls")

## Dibujar triangulo basado en altura

h = int(input("Ingrese Altura del triangulo: "))

# for i in range(h):
# 	space = " " * (h-i+1)
# 	print(space+draw)
# 	draw = "*"+draw+"*"

draw = "*"
space = " "*(h-1)
for i in range(h):
	print(space+draw)
	space = space[:-1]
	draw = "*"+draw+"*"
	