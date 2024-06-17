from os import system
from csv import reader
system("cls")

## Read CSV

lectura = open('nuevo_archivo.csv', 'r', newline='\n')
lectura_csv = reader(lectura)

for i in lectura_csv:
    print(f"{i}")