/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.superrepaso;
import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Gabo's
 */
public class SuperRepaso {
    static Scanner sc =new Scanner(System.in);
    static Entrenador entrenador1;
    static Pokemon pokemon1;
    public static void main(String[] args) {
        
        boolean y=true;
        while(y){
            System.out.println("1.- Crear Entrenador");
            System.out.println("2.- Crear Pokemon");
            System.out.println("3.- Asocia entrenador a pokemon");
            System.out.println("4.- Subir de nivel");
            System.out.println("5.- Mostrar Pokemon");
            System.out.println("6.- Salir");
            System.out.println("7.- Cambia tipo");
            System.out.println("8.- Sube de nivel por experiencia");
           
            int op=Validar.validar_int();
            switch(op){
                case 1:
                    CreaEntre();
                    break;
                case 2:
                    CreaPoke();
                    break;
                case 3:
                    asociarPoke();
                    break;
                case 4:
                    System.out.println("El nivel actual es "+pokemon1.getNivel());
                    levelUp();
                    System.out.println("El nuevo nivel es "+pokemon1.getNivel());
                    break;
                case 5:
                    System.out.println(pokemon1);
                    break;
                case 6:
                    System.out.println("Saliendo");
                    y=false;
                    break;
                case 7:
                    System.out.println("Ingrese el nuevo tipo");
                    String tipo=sc.next();
                    piedraTipo(tipo);
                    System.out.println("El nuevo tipo del pokemon es "+tipo);
                case 8:
                    int exp=0;
                    exp = (int)(Math.random()*100);
                        System.out.println("La experiencia ganada fue "+exp);
                    if(exp>70){
                        levelUp();
                    }
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
                    
            }
        }

        
        
        
    }
    
    public static void CreaEntre(){
        System.out.println("Cree un entrenador");
        System.out.println("Ingrese el nombre del Entrenador");
        String entr=sc.next();
        System.out.println("Ingrese el genero del Entrenador (M/F)");
        String gen=Validar.validaGenero();
        System.out.println("Ingrese el codigo del Entrenador");
        int cod=Validar.validar_int();
        entrenador1=new Entrenador(entr,gen,cod);
        System.out.println(entrenador1);
    }
        public static void CreaPoke(){
        System.out.println("Cree un pokemon");
        System.out.println("Ingrese el nombre del Pokemon");
        String entr=sc.next();
        System.out.println("Ingrese el tipo del pokemon");
        String gen=sc.next();
        System.out.println("Ingrese el nivel del Pokemon");
        int cod=sc.nextInt();
        pokemon1=new Pokemon(entr,gen,cod,null);
        System.out.println(pokemon1);
    }
        public static void asociarPoke(){
            pokemon1.setEntrenador(entrenador1);
            System.out.println("Pokemon asociado con exito al entrenador "+ entrenador1.getNombre());
            System.out.println(pokemon1.getEntrenador());
       
        }
        public static void levelUp(){
            pokemon1.setNivel(pokemon1.getNivel()+1);
        }
        
        public static void piedraTipo(String tipo){
            pokemon1.setTipo(tipo);
        }
}
