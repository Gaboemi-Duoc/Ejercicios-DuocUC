num1 = int(input("Numero 1: ")) 
num2 = int(input("Numero 2: "))
num3 = int(input("Numero 3: "))

valor = 0
while True:
	print("""Seleccione Opcion
			1- Sumar 
			2- Restar
			3- Multiplicar
			4- Dividir 
			5- Sumar 3 Numeros
		""")

	valor = int(input("Elige una opcion: ") )     

	if valor == 1:
		print("la suma es",num1+num2)
		break;
	elif valor == 2:
		print("la resta es",num1-num2)
		break;
	elif valor == 3:
		print("la multiplicacion es",num1*num2)
		break;
	elif valor == 4:
		print("la division es",num1/num2)
		break;
	elif valor == 5:
		print("la suma es",num1+num2+num3)
		break;
	else:
		print("Opcion incorrecta")