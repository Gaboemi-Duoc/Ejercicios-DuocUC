from os import system

system("cls")

## Supermercado costo cero
## Vende los Siguientes productos
# Arroz 1490
# Fideos 900
# Atun 1120
# Carne 3200

## mostrar opciones disponibles
## el cliente elige 1 y luego ingresa la cantidad
## finalmente consultar metodo de pago
# Efectivo  <- 10% descuento
# Debito    <- 3% descuento
# Credito   <- 4% impuesto/sobrecarga


total = 0

print("Bienvenido al Supermercado Costo Cero!")
print(f"1. Arroz\t$1490 \n2. Fideos\t$900 \n3. Atun \t$1120 \n4. Carne\t$3200")
opcion = int(input("Elija un Producto: "))
if opcion == 1:
    precio = 1490
elif opcion == 2:
    precio = 900
elif opcion == 3:
    precio = 1120
elif opcion == 4:
    precio = 3200
numProducto = int(input("Ingrese la cantidad que va a comprar: "))

total = precio * numProducto
print(f"Precio total es de ${total}")
metodoPago = int(input("Opciones de pago:\n1. Efectivo\t10% de Descuento \n2. Debito\t3% de descuento\n3. Credito\t4% de Impuestos \nComo desea pagar?: "))
if metodoPago == 1:
    pagoPorcentaje = 1-0.1
elif metodoPago == 2:
    pagoPorcentaje = 1-0.03
elif metodoPago == 3:
    pagoPorcentaje = 1.04

total *= pagoPorcentaje
total = int(round(total))

print(f"Total a pagar: ${total}")
print(f"Gracias por su comprar! :>")