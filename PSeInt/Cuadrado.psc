Proceso Cuadrado
	Definir num, numCuadrado, i Como Entero;
	
	Escribir "Ingrese un numero:";
	Leer i;
	
	num = 1;
	Mientras num <= i Hacer
		numCuadrado = num^2;
		Escribir "El Cuadrado de ", num, " es ", numCuadrado;
		num = num + 1;
	FinMientras
	
//	Para num<-1 Hasta i Con Paso 1 Hacer
//		numCuadrado = num^2;
//		Escribir "El Cuadrado de ", num, " es ", numCuadrado;
//		num = num + 1;
//	FinPara
FinProceso
