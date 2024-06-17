from os import system

system("cls")

## Ingrese 3 numero, busque el mayor

# numberList = []
# for i in range(3):
#     numberList.append( input("Ingrese un numero: ") )

# numberList.sort(reverse=True)
# mayor = numberList[0]

number1 = input("Ingrese un numero: ")
number2 = input("Ingrese un numero: ")
number3 = input("Ingrese un numero: ")

if number1 > number2 and number1 > number3:
    mayor = number1
elif number2 > number3:
    mayor = number2
else:
    mayor = number3

print(f"El numero mayor es {mayor}")