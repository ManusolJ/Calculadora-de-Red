public class ClaseDeIP {
    public static String TipoIP(String ip) {
        String clase = "";

        String primerbyte = ip.substring(0, ip.indexOf("."));//Coge el primer byte
        char caracterip;
        int punto = 0;
        String segundobyte = "";
        String tercerbyte = "";
        String cuartobyte = "";
        for (int contip = 0; contip < ip.length(); contip++) {//Coge el segundo, tercero y cuarto byte.
            caracterip = ip.charAt(contip);
            if (caracterip == '.') {
                punto++;
            }
            if (punto == 1 && caracterip != '.') {
                segundobyte = segundobyte + caracterip;
            } else if (punto == 2 && caracterip != '.') {
                tercerbyte = tercerbyte + caracterip;
            } else if (punto == 3 && caracterip != '.')
                cuartobyte = cuartobyte + caracterip;
        }

        int primbyte = Integer.parseInt(primerbyte);
        int segbyte = Integer.parseInt(segundobyte);
        //Para cosas extras
        int terbyte = Integer.parseInt(tercerbyte);
        int cuabyte = Integer.parseInt(cuartobyte);

        if (primbyte == 169 && segbyte == 254)//Apartado extra
            clase = clase + "Ip privada de Clase B.\nEs una dirección APIPA.";
        else if (primbyte >= 0 && primbyte < 128) {//Según los bytes, te dice la clase, si es privada o publica o si es una dirección reservada.
            if (primbyte == 10)
                clase = "Ip privada de Clase A.";
            else
                clase = "Ip pública de Clase A.";
        } else if (primbyte >= 128 && primbyte < 192) {
            if (segbyte >= 16 && segbyte <= 31)
                clase = "Ip privada de Clase B.";
            else
                clase = "Ip pública de Clase B.";
        } else if (primbyte >= 192 && primbyte < 224) {
            if (segbyte == 168)
                clase = "Ip privada de Clase C.";
            else
                clase = "Ip pública de Clase C.";
        } else if (primbyte >= 224 && primbyte < 240)//Apartado extra
            clase = "Ip de Clase D. Es una dirección reservada para multicast";
        else if (primbyte >= 240 && primbyte < 256)//Apartado extra
            clase = "Ip de Clase E. Es una dirección experimental.";

        //Aparetado extra
        if (primbyte == 127)
            clase = clase + "\n" + "Es una dirección reservada para loopback.";
        else if (primbyte == 192 && segbyte == 0 && terbyte == 2)
            clase = clase + "\n" + "Es una dirección reservada para TEST-NET.";

        return clase;//Resultado
    }
}
