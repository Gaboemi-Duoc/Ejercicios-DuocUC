from examenFinalFunciones import *

trabajadores =  ['Juan Pérez','María García','Carlos López','Ana Martínez','Pedro Rodríguez','Laura Hernández','Miguel Sánchez','Isabel Gómez','Francisco Díaz','Elena Fernández']
datos = []

print('Bienvenido al Programa de Sueldos!')
opc = -1
while opc !=5:
    print('1. Asignar Sueldos Aleatorios a los Trabajadores \n2. Clasificar Sueldos \n3. Ver Estadísticas \n4. Reporte de Sueldos \n5. Salir del Programa \nIngrese una opcion:')
    try: opc = int(input('> '))
    except: opc = -1
    
    if opc == 1:
        datos = asignarSueldo(trabajadores)
    elif opc == 2:
        clasificarSueldos(datos)
    elif opc == 3:
        verEstadisticas(datos)
    elif opc == 4:
        reporteSueldos(datos)
    elif opc == 5:
        salir()
    else:
        print('Opcion invalida, porfavor ingrese un valor valido...')
