from random import randint
from csv import reader, writer

def asignarSueldo(trabajadores):
    datos = []
    for i in trabajadores:
        sueldoRand = randint(300_000,2_500_000)
        datos.append((i,sueldoRand))

    print(f'Sueldos Asignados Exitosamente a {len(trabajadores)} Trabajadores...')
    return datos

def clasificarSueldos(datos):
    if len(datos) > 0:
        menores = []
        entres = []
        superiores = []
        total = 0
        for i in datos:
            sueldo = i[1]
            total += sueldo
            if sueldo < 800_000:
                menores.append(i)
            elif sueldo > 2_000_000:
                superiores.append(i)
            else:
                entres.append(i)

        # Sueldos menor
        print(f'Sueldos menores a $800 000 \nTOTAL: {len(menores)}\n')
        if len(menores) > 0:
            print('Nombre empleado \t Sueldo')
            for i in menores:
                print(f'{i[0]}      \t {i[1]}')
            print()
        # Sueldos entre $800 000 y $2 000 000
        print(f'Sueldos entre $800 000 y $2 000 000 \nTOTAL: {len(entres)}\n')
        if len(entres) > 0:
            print('Nombre empleado \t Sueldo')
            for i in entres:
                print(f'{i[0]}      \t {i[1]}')
            print()
        # Sueldos superiores a $2 000 000
        print(f'Sueldos superiores a $2 000 000 \nTOTAL: {len(superiores)}\n')
        if len(superiores) > 0:
            print('Nombre empleado \t Sueldo')
            for i in superiores:
                print(f'{i[0]}      \t {i[1]}')
            print()

        print(f'TOTAL SUELDOS: $ {total}\n\n')
    else:
        print('Los Sueldo no han sido asignados todavia! Por favor asignelos...')
    
def verEstadisticas(datos):
    if len(datos) > 0:
        sueldoBajo = 9999999 # Ningun sueldo deberia ser mayor a 9 999 999
        sueldoAlto = 0
        totalSuma = 0
        totalMultiplo = 1
        for i in datos:
            sueldo = i[1]
            totalSuma += sueldo
            totalMultiplo *= sueldo
            if sueldo < sueldoBajo:
                sueldoBajo = sueldo
            elif sueldo > sueldoAlto:
                sueldoAlto = sueldo
        
        # Sueldo mas Alto
        print(f'Sueldo más alto: $ {sueldoAlto}')
        # Sueldo mas Bajo
        print(f'Sueldo más bajo: $ {sueldoBajo}')
        # Promedio de Sueldos
        average = round(totalSuma / len(datos))
        print(f'Promedio de Sueldos: $ {average}')
        # Media Geometrica
        mg = round(totalMultiplo ** (1/len(datos)))
        print(f'Media Geometrica de Sueldos: $ {mg}\n\n')
        
    else:
        print('Los Sueldo no han sido asignados todavia! Por favor asignelos...')

def reporteSueldos(datos):
    if len(datos) > 0:
        fullDatos = []
        # Detalle Sueldos con
        # Descuento Salud 7%
        # Descuento AFP 12%
        # Sueldo liquido al substractar lo anterior
        print('Nombre empleado \t Sueldo Base \t Descuento Salud  Descuento AFP\t Sueldo Líquido')
        for i in datos:
            name = i[0]
            sueldo = i[1]
            desSalud = round(sueldo * .07)
            desAFP = round(sueldo * .12)
            liquid = sueldo - desSalud - desAFP
            
            print(f'{name}      \t {sueldo} \t {desSalud}     \t  {desAFP}    \t {liquid}')

            fullDatos.append( (name, sueldo, desSalud, desAFP, liquid) )
        
        # Exportar a un archivo .csv

        file = open('examenFinalDatos.csv', 'w+', newline='')
        fileWriter = writer(file)
        fileWriter.writerow(('Nombre empleado','Sueldo Base','Descuento Salud','Descuento AFP','Sueldo Liquido'))
        fileWriter.writerows(fullDatos)
        file.close()
        print('\n\nSe ha creado un archivo .csv con los datos de forma exitosa!\n')
    else:
        print('Los Sueldo no han sido asignados todavia! Por favor asignelos...')

def salir():
    print('Finalizando el programa...')
    print('Desarrollado por Gabriel Jeria Mayerovich')
    print('RUT 21.009.174-2')
