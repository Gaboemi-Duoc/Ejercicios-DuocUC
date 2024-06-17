from os import system
import json
import requests
system("cls")

URL = "https://swapi.dev/"

r = requests.get( url = URL )

# Esto es como uno llama a una API en internet ;>