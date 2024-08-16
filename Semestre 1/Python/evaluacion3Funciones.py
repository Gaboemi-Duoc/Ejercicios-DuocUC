
def validarINT(min=0,max=100):
    while True:
        try:
            n = int(input('> '))
            if n >= min and n <= max:
                return n
            else:
                print(f'Numero Ingresado fuera de rango: {min} - {max}')
        except: print('ERROR! Tipo de Dato no Valido...')
        

def mostrarEstacionamiento(estacionamiento):
    print()
    print('X = Asiento Reservado')
    print('O = Asiento Disponible')
    print()

    print('   1 2 3 4 5 6 7 8 9 1 1 1')
    print('                     0 1 2')
    print()
    fila = 1
    for i in estacionamiento:
        print(fila, end='  ')
        for e in i:
            if type(e) == type(int()):
                print('O', end=' ')
            else:
                print('X', end=' ')
        print()
        fila += 1
            
def reservarEstacionamiento(estacionamiento):
    print('Precios por Fila:')
    print('- 1 y 2:\t$6000')
    print('- 3,4,5 y 6:\t$1500')
    print('Ingresa el numero de Fila: ')
    fila = validarINT(1,6) - 1
    print('Ingresa el numero del Espacio: ')
    columna = validarINT(1,12) - 1
    if type(estacionamiento[fila][columna]) != type(int()):
        print('Ese Asiento ya esta reservado, por favor intente otro asiento...')
    else:
        print('Ingrese su Nombre:')
        name = input('> ')
        print('Ingrese la Matricula del Vehiculo:')
        matricula = input('> ')
        estacionamiento[fila][columna] = (name,matricula)
        
    
def anularEstacionamiento(estacionamiento):
    print('Anulando reserva...')
    print('Ingresa el numero de Fila: ')
    fila = validarINT(1,6) - 1
    print('Ingresa el numero del Espacio: ')
    columna = validarINT(1,12) - 1
    if type(estacionamiento[fila][columna]) != type(int()):
        estacionamiento[fila][columna] = 0
    else:
        print('Este asiento no tiene una reserva...')
def calcularIngresos(estacionamiento):
    fila = 1
    ingresos = 0
    for i in estacionamiento:
        for e in i:
            if type(e) == type(int()):
                pass
            else:
                if fila > 2:
                    ingresos += 1500
                else: ingresos += 6000
        fila += 1
    print(f'Los Ingresos del dia serian de ${ingresos}!')
def mostrarNReservas(estacionamiento):
    NReservas = 0
    for i in estacionamiento:
        for e in i:
            if type(e) == type(int()):
                pass
            else:
                NReservas += 1
    print(f'Hay {NReservas} Espacios Reservados en el Estacionamiento Actualmente')

                
