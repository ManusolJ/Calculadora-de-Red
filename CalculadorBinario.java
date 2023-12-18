import java.util.Scanner;

public class CalculadorBinario {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la IP");
        String ip = sc.next();
        System.out.println("Máscara");
        int masc = sc.nextInt();
        char c;
        for(int i = 0;i < ip.length();i++){
            c = ip.charAt(i);
            if(c < 46 || c > 57 || c == 47 || !ip.contains(".") || ip.length() > 16){
                System.out.println("Error! Debes introducir una IP valida.");
                ip = sc.next();
                i = 0;
            }
        }

        String [] ipElementos = ip.split("\\.");
        String resultado = "";
        for(String octavo : ipElementos){
            int oct = Integer.parseInt(octavo);
            String binario = Integer.toBinaryString(oct) + ".";
            while(binario.length() != 9){
                binario = 0 + binario;
            }
            resultado += binario;
        }



       System.out.print(resultado + "\b");
        System.out.println(" ");
     System.out.println(dirRed(resultado, masc));
        System.out.println(dirBroadcast(resultado,masc));

    }
    public static String dirRed(String resultado, int masc){
        resultado = resultado.replace(".",""); //se quitan los puntos
        String binarito = resultado.substring(0, (masc)); //la substring binarito es la parte que no se sustituye por 0

        while(binarito.length() < 32){ //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "0";
        }
                return binarito;
    } 
}
    public static String dirBroadcast(String resultado, int masc){
        resultado = resultado.replace(".",""); //se quitan los puntos
        String binarito = resultado.substring(0, (masc)); //la substring binarito es la parte que no se sustituye por 0

        while(binarito.length() < 32){ //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "1";
        }
        return binarito;
    }
}