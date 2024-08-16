Proceso SumAcumulativa
	Definir num, total Como Entero;
	total = 0;
	
	Escribir "Ingrese un numero:";
	Leer num;
	
	Mientras num <> 0 Hacer
		total = total + num;
		
		Escribir "Ingrese otro numero";
		Escribir "La suma es ", total;
		Escribir "Para salir, presione 0";
		
		Leer num;
	FinMientras
	
FinProceso
