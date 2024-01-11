public class PruebasRedYBroadcast {



    public static String dirBroadcastDec (String resultado,String masc){
        //Poner la mascara en binario.
        masc = masc.replace(".","");
        int rmasc = 0;
        for (int contmasc = 0; contmasc<masc.length();contmasc++){
            if (masc.charAt(contmasc) == 1){
                rmasc++;
            }
        }
        //int rmasc = Integer.parseInt(masc);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "1";
        }
        return binarito;

    }

    public static String dirRedDec (String resultado,String masc){
        //Poner la mascara en binario.
        masc = masc.replace(".","");
        int rmasc = 0;
        for (int contmasc = 0; contmasc<masc.length();contmasc++){
            if (masc.charAt(contmasc) == 1){
                rmasc++;
            }
        }
        //int rmasc = Integer.parseInt(masc);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "0";
        }
        return binarito;
    }
}

