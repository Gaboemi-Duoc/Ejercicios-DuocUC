from os import system
system("cls")

print("Bienvenido al mundo de la programación")
name = input("Para comenzar, ingresa tu nombre: ")

print(f"Bienvenido {name}")

x = int(input("Ingrese x para resolve la siguiente ecuacion:\n\t(x^2 + 3x + 1) / 4\nx = "))
resultadoEcuacion = (x^2 + 3*x + 1) / 4
print(f"({x}^2 + 3{x} + 1) / 4 = {resultadoEcuacion}")

nombre = input("Ingrese su nombre completo: ").upper()
rut = ""
while not( len(rut) == 9 or len(rut) == 10 or len(rut) == 12 ):
	rut = str(input("Ingrese su Rut: ")).upper()
	if len(rut) == 9 or len(rut) == 12:
		if "." in rut or "-" in rut:
			rut = rut.replace(".", "")
			rut = rut.replace("-", "")
		rut = rut[0]+rut[1]+"."+rut[2]+rut[3]+rut[4]+"."+rut[5]+rut[6]+rut[7]+"-"+rut[8]
	else:
		print("Formato Invalido, ingreselo de nuevo...")

correo = input("Ingrese su correo: ").upper()
numeroTelefono = str(input("Ingrese su número de telefono: ")).upper()

print(
	f"NOMBRE:\t\t{nombre}\n" +
	f"RUT:\t\t{rut}\n" +
	f"CORREO:\t\t{correo}\n" +
	f"TELEFONO:\t{numeroTelefono}"
)
