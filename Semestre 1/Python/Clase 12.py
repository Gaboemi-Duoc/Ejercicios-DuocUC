from os import system
system("cls")

file = open('textFile.txt', 'w', newline="")
nombre = """Este es un archivo de texto simple que no tiene
ningún formato en particular, lo podemos utilizar
para guardar todo tipo de texto.
"""
file.write(nombre)
print('!')
file.close()