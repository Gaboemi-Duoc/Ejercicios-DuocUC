Proceso AdivinaLaSecuela
	Definir numAleatorio, numIntento, intentos Como Entero;
	numIntento <- 0;
	numAleatorio <- Aleatorio(1,100);
	
	Escribir "Cuantos intentos quieres tener?";
	Leer intentos;
	
	Mientras intentos > 0 Hacer
		Escribir "Ingrese un numero:";
		Leer numIntento;
		
		Si numIntento == numAleatorio Entonces
			Escribir "Felicidades, adivinaste, el numero es ", numAleatorio, "! :>";
			intentos = -1;
		SiNo
			intentos = intentos - 1;
			
            Escribir "Número incorrecto. Te quedan ", intentos, " intentos.";
			
			Si numIntento < numAleatorio Entonces
				Escribir "Pista: El numero adivino es mayor al ingresado!";
			SiNo
				Escribir "Pista: El numero adivino es menor al ingresado!";
			FinSi
		FinSi
		
	FinMientras
	
	Si numIntento <> numAleatorio Entonces
		Escribir "Has perdido! El numero a adivinar era ", numAleatorio;
	FinSi
FinProceso
