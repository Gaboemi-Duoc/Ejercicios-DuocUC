from os import system
import json
system("cls")

## Read JSON

lectura = open('nuevo_documentos.json', "r")
lectura_json = json.load(lectura)

print(lectura_json)