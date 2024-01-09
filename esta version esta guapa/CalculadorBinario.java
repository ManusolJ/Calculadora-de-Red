import java.util.Scanner;

public class CalculadorBinario {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la IP");
        String ip = sc.next(); //Usuario introduce IP por teclado.

        char c;
        String mascarad = "";
        String mascaracdir = "0";

        for (int i = 0; i < ip.length(); i++) {
            c = ip.charAt(i);
            if (c < 46 || c > 57 || c == 47 || !ip.contains(".") || ip.length() > 16) {
                System.out.println("Error! Debes introducir una IP valida.");
                ip = sc.next();
                i = 0;
            }
        } //Se valida la IP introducida.


        String opcionmasc = "0";
        while (!opcionmasc.matches("[1-2]")) {
            try {
                System.out.println("¿Qué formato de máscara quieres?\n1. Notación CDIR\n2. Notación decimal");
                opcionmasc = sc.next();
                if (!opcionmasc.matches("[1-2]")) throw new Exception("Error, opción incorrecta. Vuelve a intentarlo.");

            } catch (Exception e) {
                System.out.println("Error, opción incorrecta.");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        switch (Integer.parseInt(opcionmasc)) {
            case 1:

                System.out.println("Notación CDIR elegida.");
                while (!mascaracdir.matches("^/[0-3]?[0-9]") || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) > 32 || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) <= 0) {
                    try {
                        System.out.print("Escribe la máscara con notación CDIR: ");
                        mascaracdir = sc.next();
                        if (!mascaracdir.matches("^/[0-3]?[0-9]") || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) > 32 || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) <= 0)
                            throw new Exception("Error al introducir la máscara con notación CDIR. Prueba de nuevo.");
                        System.out.println("Máscara CDIR correcta.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                }
                break;
            case 2:

                System.out.println("Notación decimal elegida.");
                boolean repite = true;
                while (repite) {
                    try {
                        System.out.print("Escribe la máscara con notación decimal: ");
                        mascarad = sc.next();
                        boolean resultado = true;
                        int[] nummasc = {0, 128, 192, 224, 240, 248, 252, 254};//Bytes de máscara posibles cuando no es 255
                        if (mascarad.matches("[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]")) {
                            String[] mascElementos = mascarad.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
                            boolean es0 = true;
                            for (int pricont = 0; pricont < mascElementos.length; pricont++) {
                                int mascbyte = Integer.parseInt(mascElementos[pricont]);
                                if (mascbyte < 0 || mascbyte > 255 || Integer.parseInt(mascarad.substring(0, mascarad.indexOf("."))) == 0 && resultado) {//Comprueba si el primer número esta entre el 0 y el 255 y comprueba que el primer byte no sea 0.
                                    resultado = false;
                                } else if (mascbyte != 255 && resultado && es0) {//Cuando el byte no sea 255, se hace y comprueba que sea uno de los números del array, ya que no puede ser diferente, una vez se hace se desactiva con el boolean es0.
                                    for (int cont = 0; cont < nummasc.length; cont++) {
                                        if (mascbyte == nummasc[cont]) {
                                            resultado = true;
                                            cont = nummasc.length;
                                            es0 = false;
                                        } else
                                            resultado = false;
                                    }
                                } else if (!es0 && mascbyte != 0) {//Después de que el anterior byte sea distinto a 255, comprueba que los siguientes sean 0.
                                    resultado = false;
                                    pricont = mascElementos.length;
                                }
                            }
                            if (resultado)
                                repite = false;
                        } else
                            resultado = false;

                        if (!resultado)//Devuelve el error si no es válido.
                            throw new NumberFormatException("Error al introducir la máscara con notación decimal. Prueba de nuevo.");
                        System.out.println("Máscara en decimal correcta.");
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }

                }
                break;
        }

        String[] ipElementos = ip.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
        String resultado = "";

        for (String octavo : ipElementos) {   //Se asigna cada elemento del array a un String,cada vez que se itera el bucle.
            int oct = Integer.parseInt(octavo); //Se transforma dicho String en un int.
            String binario = Integer.toBinaryString(oct) + "."; //Este int se transforma en binario mediante un funcion.
            while (binario.length() != 9) {   //Como los zeros a la izquierda se ignoran,se añade esta parte para que hayan exactamente 8 digitos en el octeto.
                binario = 0 + binario;  //Se crea un String con todos los octetos.
            }
            resultado += binario;
        }

        resultado = resultado.replace(".", ""); //Quita los puntos del String.

        String broadcast = "";
        String mascara = mascaracdir; //La mascara elegida.

        //Segun el tipo de mascara elegida.
        if(opcionmasc == "1") {
            broadcast = dirBroadcast(resultado, mascara);
        }else{
            broadcast = dirBroadcast(resultado, mascara);
        } //TEMPORAL

        broadcast = insertarPunto(broadcast); //Añade los puntos al String.
        String resultadobc = "";

        String[] elementosbroadc = broadcast.split("\\.");
        for (String octetobc : elementosbroadc) {   //Se asigna cada elemento del array a un String,cada vez que se itera el bucle.
            int decimalbc = convertidorBiToDec(octetobc);//Convierte la direccion de broadcast de binario a decimal.
            String direccdeb = Integer.toString(decimalbc) + ".";//
            resultadobc = resultadobc + direccdeb;
        }
        System.out.println(resultadobc + "\b");//Imprime la direccion de broadcast en decimal.

        String red = "";

        red = dirRed(resultado,mascara);
        red = insertarPunto(red);
        String resultadoRD = "";

        String[] elementosRed = red.split("\\.");
        for(String octetoRD : elementosRed){
            int decimalRD = convertidorBiToDec(octetoRD);
            String direccRD = Integer.toString(decimalRD) + ".";
            resultadoRD = resultadoRD + direccRD;
        }
        System.out.println(resultadoRD);
    }



    public static String dirBroadcast (String resultado,String masc){
        masc = masc.replace('/','0');
        int rmasc = Integer.parseInt(masc);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "1";
        }
        return binarito;

        //IMPORTANTE FALTA AÑADIR FORMA DECIMAL

    }
    public static String dirRed (String resultado,String masc){
        masc = masc.replace('/','0');
        int rmasc = Integer.parseInt(masc);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "0";
        }
        return binarito;

        //IMPORTANTE FALTA AÑADIR FORMA DECIMAL
    }

    //Esta funcion convierte de binario a decimal.
    public static int convertidorBiToDec (String binario){
        int decimal = Integer.parseInt(binario, 2);
        return decimal;
    }
    //Esta funcion inserta puntos en un String.
    public static String insertarPunto (String bag){
        String[] nuevoOctavo = new String[bag.length() / 8];
        int s = 0;
        int e = 8;
        for (int i = 0; i < 4; i++) {
            nuevoOctavo[i] = bag.substring(s, e);
            s = s + 8;
            e = e + 8;
        }
        String resultado = "";
        for (int ii = 0; ii < 4; ii++) {
            resultado += nuevoOctavo[ii] + ".";
        }
        resultado = resultado.substring(0, 35);
        return resultado;
    }
}
