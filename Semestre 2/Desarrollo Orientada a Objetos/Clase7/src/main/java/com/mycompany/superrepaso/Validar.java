
package com.mycompany.superrepaso;
import static com.mycompany.superrepaso.SuperRepaso.sc;
/**
 *
 * @author Gabo's
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
