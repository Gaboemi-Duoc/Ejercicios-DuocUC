/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.superrepaso;
import static com.mycompany.superrepaso.SuperRepaso.sc;
/**
 *
 * @author pvespucio
 */
public class Validar {
       public static int validar_int(){
        String input;
        int num = 0;
        while(true){
            try{
                input = sc.next();
                num = Integer.parseInt(input);
                break;
            }
            catch(NumberFormatException e){
                System.out.println("Error. Ingrese un número entero.");
            }
        }return num;
    }
       public static String validaGenero(){
           boolean x=true;
           String genero="";
           while(x){
           genero=sc.next();
           if("m".equals(genero.toLowerCase())||"f".equals(genero.toLowerCase())){
               x=false;
               
           }else{
               System.out.println("Invalido, solo debe ingrese M o F");
           }
           
           }
          return genero;
           
       }
}