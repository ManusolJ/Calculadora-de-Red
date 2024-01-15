import java.util.Scanner;
import org.apache.commons.validator.routines.InetAddressValidator;

public class Funciones {

    public String insertarPunto (String bag){
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

    public  int bitodecConv (String binario){
        return Integer.parseInt(binario, 2);
    }

    public String dirBC (String resultado,String mascara){
        int rmasc = Integer.parseInt(mascara);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "1";
        }
        return binarito;
    }

    public  String dirRD (String resultado,String mascara){
        mascara = mascara.replace('/','0');
        int rmasc = Integer.parseInt(mascara);
        resultado = resultado.replace(".", ""); //se quitan los puntos
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "0";
        }
        return binarito;

    }

    public  String dirBCdec (String resultado,String mascara){
        int rmasc = 0;
        for (int contmasc = 0; contmasc<mascara.length();contmasc++){
            if (mascara.charAt(contmasc) == '1'){
                rmasc++;
            }
        }
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "1";
        }
        return binarito;
    }

    public  String dirRDdec (String resultado,String mascara){
        int rmasc = 0;
        for (int contmasc = 0; contmasc<mascara.length();contmasc++){
            if (mascara.charAt(contmasc) == '1'){
                rmasc++;
            }
        }
        String binarito = resultado.substring(0, (rmasc)); //la substring binarito es la parte que no se sustituye por 0

        while (binarito.length() < 32) { //añade 0s hasta que binarito sea tan largo como resultado
            binarito = binarito + "0";
        }
        return binarito;
    }

    public String maskType (String opcionmasc) {
        Scanner sc = new Scanner(System.in);
        while (!opcionmasc.matches("[1-2]")) {
            try {
                System.out.println("¿Qué formato de máscara quieres?\n1. Notación CIDR\n2. Notación decimal");
                opcionmasc = sc.next();
                if (!opcionmasc.matches("[1-2]")) throw new Exception("Error, opción incorrecta. Vuelve a intentarlo.");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        return opcionmasc;
    }
    public String maskInput (String opc){
    Scanner sc = new Scanner(System.in);
        String mascara = "0";
        switch (Integer.parseInt(opc)) {
            case 1:
                System.out.println("Notación CIDR elegida.");
                while (!mascara.matches("^[0-3]?[0-9]") || Integer.parseInt(mascara.substring(0, mascara.length())) > 32 || Integer.parseInt(mascara.substring(0, mascara.length())) <= 0) {
                    try {
                        System.out.print("Escribe la máscara con notación CIDR: ");
                        mascara = sc.next();
                        if (!mascara.matches("^[0-3]?[0-9]") || Integer.parseInt(mascara.substring(0, mascara.length())) > 32 || Integer.parseInt(mascara.substring(0, mascara.length())) <= 0)
                            throw new Exception("Error al introducir la máscara con notación CIDR. Prueba de nuevo.");
                        System.out.println("Máscara CIDR correcta.");
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
                    try{
                        System.out.print("Escribe la máscara con notación decimal: ");
                        mascara = sc.next();
                        boolean resultado = true;
                        int [] nummasc = {0,128,192,224,240,248,252,254};//Bytes de máscara posibles cuando no es 255
                        if (mascara.matches("[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]")){
                            String [] mascElementos = mascara.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
                            boolean es0 = true;
                            for(int pricont = 0; pricont<mascElementos.length; pricont++){
                                int mascbyte = Integer.parseInt(mascElementos[pricont]);
                                if (mascbyte < 0 || mascbyte > 255 || Integer.parseInt(mascara.substring(0,mascara.indexOf("."))) == 0 && resultado){//Comprueba si el primer número esta entre el 0 y el 255 y comprueba que el primer byte no sea 0.
                                    resultado = false;
                                }else if (mascbyte != 255 && resultado && es0){//Cuando el byte no sea 255, se hace y comprueba que sea uno de los números del array, ya que no puede ser diferente, una vez se hace se desactiva con el boolean es0.
                                    for (int cont = 0; cont < nummasc.length; cont++){
                                        if (mascbyte == nummasc[cont]) {
                                            resultado = true;
                                            cont = nummasc.length;
                                            es0 = false;
                                        }else
                                            resultado = false;
                                    }
                                }else if (!es0 && mascbyte!=0){//Después de que el anterior byte sea distinto a 255, comprueba que los siguientes sean 0.
                                    resultado = false;
                                    pricont = mascElementos.length;
                                }
                            }
                            if (resultado)
                                repite = false;
                        }else
                            resultado=false;

                        if (!resultado)//Devuelve el error si no es válido.
                            throw new NumberFormatException("Error al introducir la máscara con notación decimal. Prueba de nuevo.");
                        System.out.println("Máscara en decimal correcta.");
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                }
        }
        return mascara;
    }

    public String clasificadorIP (String ip){
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

        if (primbyte == 169 && segbyte == 254)//Apartado extra
            clase = clase + "Ip privada de Clase B.\nEs una dirección APIPA.";
        else if (primbyte >= 0 && primbyte < 128) {//Según los bytes, te dice la clase, si es privada o publica o si es una dirección reservada.
            if (primbyte == 10)
                clase = "Ip privada de Clase A.";
            else
                clase = "Ip pública de Clase A.";
        } else if (primbyte >= 128 && primbyte < 192) {
            if (primbyte == 172 && segbyte >= 16 && segbyte <= 31)
                clase = "Ip privada de Clase B.";
            else
                clase = "Ip pública de Clase B.";
        } else if (primbyte >= 192 && primbyte < 224) {
            if (primbyte == 192 && segbyte == 168)
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

    public int calculaNumeroHosts(String a){
        int mascara = Integer.parseInt(a);
        return (int) Math.pow(2,(32 - mascara))-2;
    }

    public int calcularNumeroHostDec(String a){
        return (int) Math.pow(2,32 - convertidorMasc(a)) - 2;
    }

    public String rango(String x,String y) {
        int a = Integer.parseInt(x.substring(x.lastIndexOf(".") + 1, x.length())) + 1;
        String primdir = x.substring(0, x.lastIndexOf(".") + 1) + a;

        int i = Integer.parseInt(y.substring(y.lastIndexOf(".") + 1, y.length())) - 1;
        String ultdir = y.substring(0, y.lastIndexOf(".") + 1) + i;

        return primdir + " - " + ultdir;
    }

    public int convertidorMasc(String a){
        int rmasc = 0;
        for (int contmasc = 0; contmasc < a.length(); contmasc++) {
            if (a.charAt(contmasc) == '1') {
                rmasc++;
            }
        }
        return rmasc;
    }

    public boolean validator (String ip) {
        boolean a = false;
        if(InetAddressValidator.getInstance().isValid(ip) == true){
            a = true;
        }else a = false;
        return a;
    }


}
