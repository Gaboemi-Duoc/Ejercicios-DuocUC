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
precio = [1490, 900, 1120, 3200]

print("Bienvenido al Supermercado Costo Cero!")
print(f"1. Arroz\t${precio[0]} \n2. Fideos\t${precio[1]} \n3. Atun \t${precio[2]} \n4. Carne\t${precio[3]}")
opcion = int(input("Elija un Producto: "))
numProducto = int(input("Ingrese la cantidad que va a comprar: "))

total = precio[opcion-1] * numProducto
print(f"Precio total es de ${total}")
pagoPorcentaje = [1-0.1, 1-0.03, 1.04]
metodoPago = int(input("Opciones de pago:\n1. Efectivo\t10% de Descuento \n2. Debito\t3% de descuento\n3. Credito\t4% de Impuestos \nComo desea pagar?: "))

total *= pagoPorcentaje[metodoPago-1]
#total = int(round(total))

print(f"Total a pagar: ${total:.0f}") ## :.(numero de decimales)f
print(f"Gracias por su comprar! :>")