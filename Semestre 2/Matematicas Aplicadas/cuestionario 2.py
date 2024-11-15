# Sea una sucesión cuyos tres primeros términos son 200, 224 y 248. Si la sucesión mantiene el mismo patrón de crecimiento sucesivamente.
# def sucesion(n): return (n-1)*24 + 200

# print(sucesion(36))

# total = 0
# for i in range(36):
#     n = i + 1
#     total += sucesion(n)
    
# print(total)

# Una cuenta de ahorros proporciona un interés mensual del 1,2%. La cuenta se abre con $2.000.000.
# def cuentaAhorro(n): return 2_000_000 * 1.012 **n

# print(cuentaAhorro(9))


# Se ha obtenido que el segundo mes, el rendimiento del servidor es 1.066 TPS y el quinto mes, el rendimiento es de 8.528 TPS.
# print(f'{(8.528/1.066)**(1/3):.1f}')
# razon de 2
# 1.066 = a1 * 2
# print(1.066 / 2) = 0.533
# def rendimientoTPS(n): return 0.533 * 2 **(n-1)

# print(rendimientoTPS(9))
# n = 1
# while 34.112 != rendimientoTPS(n):
#     n += 1
# print(n)

# En el cuarto mes, el valor del arriendo fue $1.108.000 y en el décimo segundo mes el valor del arriendo fue $1.396.000.
# print((1396000 - 1108000)/8)
# print(1108000 -  36000*3)
# def arriendoComercio(n): return 1000000 + 36000 * (n-1)
# print(arriendoComercio(1))
# n = 1
# while 1828000 != arriendoComercio(n):
#     n += 1
# print(n)

import numpy

# T = numpy.array([ # Plantas Produccion x Bodega
#     [300, 272, 240],
#     [260, 180, 324]
# ])

# U = numpy.array([ # Bodega x Costo almacenamiento Mes
#     [1200, 1250],
#     [1100, 1400],
#     [1350, 1200]
# ])

# D = numpy.dot(T, U) # Plantas x Mes

# print(D)

# S = numpy.array([
#     [360, 280, 150, 420],
#     [450, 370, 210, 130],
#     [240, 260, 320, 340]
# ])

# C = numpy.array([
#     [130, 310, 230, 280],
#     [270, 240, 520, 370],
#     [190, 290, 460, 410]
# ])

# newS = S * (1-0.2)
# newC = C * (1+0.1) 

# T = newS + newC
# print(T[1][2])

# A = numpy.fromfunction( lambda i,j: 2*(i+1) + 3*(j+1), (3,3) )
# B = numpy.fromfunction( lambda i,j: 10*(i+1)*(j+1), (3,3) )
# C = numpy.dot(A,B)
# print(C)

# operatoria = numpy.array([
#     [2,  1],
#     [3, -2]
# ])
# resultado = numpy.array([
#     [10],
#     [8]
# ])

# inv_ope = numpy.linalg.inv(operatoria)
# xy = numpy.dot(inv_ope, resultado)
# print(xy)

# A = numpy.array([
#     [2, 3],
#     [1, 2]
# ])

# print(numpy.linalg.inv(A))
# print(A.T)
# print(A + A.T)

# op = numpy.array([
#     [2, 3],
#     [1, 4]
# ])
# res = numpy.array([
#     [52],
#     [56]
# ])

# inv_op = numpy.linalg.inv(op)

# solucion = numpy.dot(inv_op,res)
# print(solucion)