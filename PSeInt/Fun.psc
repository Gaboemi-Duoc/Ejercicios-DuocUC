Proceso Fun
	Definir name, claseNombre, map Como Caracter;
	Definir clase, hp, maxHP, def, atk, range, x, z, arena Como Entero;
	Definir alive Como Logico;
	
	Escribir "Ingrese un nombre:";
	Leer name;
	
	Dimension arena[6,3];
	Para x<-0 Hasta 5 Con Paso 1 Hacer
		Para z<-0 Hasta 2 Con Paso 1 Hacer
			arena[x,z] = 0;
			//Escribir x, " ", z;
		FinPara
	FinPara
	
	clase = 0;
	Escribir "Seleccione una Clase:";
	Escribir "1. Guerrero";
	Escribir "2. Mago";
	Escribir "3. Explorador";
	Escribir "4. Paladin";
	Mientras clase < 1 o clase > 4 Hacer
		Leer clase;
		Segun clase Hacer
			1:
				claseNombre = "Guerrero";
				maxHP = 125;
			2:
				claseNombre = "Mago";
				maxHP = 80;
			3:
				claseNombre = "Explorador";
				maxHP = 100;
			De Otro Modo:
				Escribir "Seleccion invalida, porfavor intentelo de nuevo...";
		FinSegun
	FinMientras
	
	hp = maxHP;
	alive = Verdadero;
	
	map = "";
	
	Escribir "Seleccionado: ", claseNombre;
	Escribir "En este juego, para continuar dialogo apreta cualquier tecla...";
	Esperar Tecla;
	Escribir "Hola ", name, ", gran ", claseNombre ," de renombre y de varias hazañas, tales como...";
	Esperar Tecla;
	Escribir "...";
	Esperar Tecla;
	Escribir "...";
	Esperar Tecla;
	Escribir "...";
	Esperar Tecla;
	Escribir "Anyways...";
	Esperar Tecla;
	Escribir "Eres nuestro adventurero más calificado en este lugar, asi que no tienes mas opcion que enfrentarte con el Rey Demonio (subject to change)";
	Esperar Tecla;
	Escribir "Buena Suerte!";
	Mientras alive = Verdadero Hacer
		hp = 0;
		Si hp <= 0 Entonces
			alive = Falso;
		FinSi
	FinMientras
	
	
	
	
FinProceso

SubProceso hp <- Fight ( hpPlayer, idFight )
	
	
FinSubProceso

