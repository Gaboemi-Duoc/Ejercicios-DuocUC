from os import system
system("cls")

def v_num(min,max,text):
    num = min - 1
    while num < min or num > max:
        try:
            num = int(input(f"Ingrese {text}: "))
            if num < min or num > max:
                print(f"Error en el rango entre {min} y {max}...")
        except:
            print("Error! Tipo de dato invalido...")

edad = v_num(10,80,"edad")
opc = v_num(0,4,"opc")