import java.util.Scanner;

public class CalculadorBinario {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la IP");
        String ip = sc.next();
        char c;
        for(int i = 0;i < ip.length();i++){
            c = ip.charAt(i);
            if(c < 46 || c > 57 || c == 47 || !ip.contains(".") || ip.length() > 16){
                System.out.println("Error! Debes introducir una IP valida.");
                ip = sc.next();
                i = 0;
            }
        }//Se valida la IP introducida.

        String [] ipElementos = ip.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
        String resultado = "";
        for(String octavo : ipElementos){   //Se asigna cada elemento del array a un String,cada vez que se itera el bucle.
            int oct = Integer.parseInt(octavo); //Se transforma dicho String en un int.
            String binario = Integer.toBinaryString(oct) + "."; //Este int se transforma en binario mediante un funcion.
            while(binario.length() != 9){   //Como los zeros a la izquierda se ignoran,se añade esta parte para que hayan exactamente 8 digitos en el octeto.
                binario = 0 + binario;  //Se crea un String con todos los octetos.
            }
            resultado += binario;
        }
        System.out.print(resultado + "\b");
    }
}
