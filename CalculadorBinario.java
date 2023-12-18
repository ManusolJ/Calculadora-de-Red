import java.util.Scanner;

public class CalculadorBinario {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la IP");
        String ip = sc.next();
        System.out.println("Introduce la mascara");
        int mascara = sc.nextInt();

        char c;
        char a;
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
        System.out.print(resultado + "\b");//TEMPORAL ELIMINAR ANTES DE PRESENTAR
        System.out.println();//TEMPORAL ELIMINAR ANTES DE PRESENTAR

        resultado = resultado.replace(".","");//Quita los puntos del String.
        System.out.print(resultado);//TEMPORAL ELIMINAR ANTES DE PRESENTAR
        System.out.println();//TEMPORAL ELIMINAR ANTES DE PRESENTAR
        String broadcast = "";
        String masc;

        for(int i = 0;i < resultado.length();i++){
            c = resultado.charAt(i);
            a = masc.charAt(i);
            if(c == 48 && a == 48){
                broadcast = broadcast + "0";
            }
            else if(c == 49 && a == 49){
                broadcast = broadcast + "1";
            }
            else{
                broadcast = broadcast + "0";
            }
        }
        resultado = insertarPunto(resultado);
        System.out.println(resultado);
    }
    public static int convertidorBiToDec(String binario){
        int decimal = Integer.parseInt(binario,2);
        return decimal;
    }

    public static String insertarPunto(String bag){
    String []  nuevoOctavo = new String[bag.length()/8];
    int s = 0;
    int e = 8;
    for(int i = 0 ; i < 4;i++){
        nuevoOctavo[i] = bag.substring(s,e);
        s = s + 8;
        e = e + 8;
    }
    String resultado = "";
    for(int ii = 0; ii < 4;ii++){
        resultado += nuevoOctavo[ii] + ".";
    }
    resultado = resultado + "\b";
    return resultado;
    }
}
