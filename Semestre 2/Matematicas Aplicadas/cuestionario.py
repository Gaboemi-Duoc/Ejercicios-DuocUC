import numpy
import matplotlib.pyplot as plot
from scipy.optimize import fsolve

def s(x): return -1.6*x**2 + 1.3*x + 141.3 #auto
def r(x): return 1.4*x**2 - 5.6*x + 12.6 #moto

def inter(x): return s(x) - r(x)

x = numpy.linspace(-10, 10, 4)

solucion = numpy.round(fsolve(inter, x), decimals = 1)

# numpy.unique() permite eliminar las soluciones repetidas

variable = numpy.unique(solucion)

print(f'El(los) punto(s) de intersección de las funciones está(n) en x = {variable}') # 1. Recordad Dominio Contextualisado

def D(t): return 50*numpy.exp(0.3*t)
def D551(t): return D(t) - 551

xo = numpy.linspace(0, 1000, 1)
solucion = numpy.around( fsolve(D551, xo), 2 )
#print(f'{solucion[0]:.0f}')

#print(f'{D(6):.2f}')

def M(x): return x**3 - 4*x**2 + 5*x
#print(f'{M(10):.0f}')

def C(n): return 50*n + 200
def C1500(n): return C(n) -1500

xo = numpy.linspace(0, 1000, 1)
solucion = numpy.around( fsolve(C1500, xo), 2 )
#print(f'{solucion[0]:.2f}')
#print(f'{C(20):.2f}')

def S(n):return 2*n**2 + 3*n + 5
def S500(n): return S(n) - 500

xo = numpy.linspace(0, 1000, 1)
solucion = numpy.around( fsolve(S500, xo), 2 )
#print(f'{solucion[0]:.2f}')
#print(f'{S(50):.2f}')


x = numpy.array([5,10,15,20])
y = numpy.array([50,80,110,140])
pendiente, intercepto = numpy.polyfit(x, y, 1)
# print(f'La pendiente es {round(pendiente,2)}')
# print(f'El coeficiente de posición es {round(intercepto,2)}')
# print(f'La función es y = {round(pendiente,2)} * x + {round(intercepto,2)}')

def I(x): return 50*x + 0.5*x**2    #Ingreso por Sub
def C(x): return 10*x + 450         #Costos operativos
def inter(x): return I(x)-C(x)
x = numpy.linspace(-15, 15, 4)
solucion = numpy.round(fsolve(inter, x), decimals = 1)
variable = numpy.unique(solucion)

print(f'El(los) punto(s) de intersección de las funciones está(n) en x = {variable}') # 15. La respuesta estaba en miles, puse 10, eran 10 000

# def f(x): return 1 - 2*x
# x = numpy.linspace(-3, 3, 4)
# plot.plot(x, f(x), color='blue')
# plot.title('Nombre del gráfico')
# plot.ylabel('Nombre eje y')
# plot.xlabel('Nombre eje x')
# plot.grid(True)
# plot.legend()
# plot.show()