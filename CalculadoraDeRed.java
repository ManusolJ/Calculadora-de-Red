import java.util.Scanner;

public class CalculadoraDeRed {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        Funciones fn = new Funciones();

        String resultadoIPBIN = "";
        String broadcast = "";
        String red = "";
        String resultadobc = "";
        String resultadoRD = "";
        String mascarabinaria = "";

        System.out.println("Introduce la IP");

        String ip = sc.next();                     //Usuario introduce IP.

        while(!fn.validator(ip)){                  //Validacion de la IP.
            System.out.println("Error!Vuelve a introducir la IP.");
            ip = sc.next();
        }

        System.out.println("¿Qué formato de máscara quieres?\n1. Notación CIDR\n2. Notación decimal");
        String opcionmasc = fn.maskType(sc.next());             //Se elige que tipo de mascara quieres utilizar.
        String mascara = fn.maskInput(opcionmasc);              //Se introduce la mascara.

        System.out.println();

        String[] ipElementos = ip.split("\\.");           //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
        for (String octavo : ipElementos) {                     //Se asigna cada elemento del array a un String,cada vez que se itera el bucle.
            int oct = Integer.parseInt(octavo);                 //Se transforma dicho String en un int.
            String binario = Integer.toBinaryString(oct) + "."; //Este int se transforma en binario mediante un funcion.
            while (binario.length() != 9) {                     //Como los zeros a la izquierda se ignoran,se añade esta parte para que hayan exactamente 8 digitos en el octeto.
                binario = 0 + binario;                          //Se crea un String con todos los octetos.
            }
            resultadoIPBIN += binario;
        }

        resultadoIPBIN = resultadoIPBIN.replace(".","");  //Se quitan los puntos para futuras operaciones.


        if(opcionmasc.equals("2")) {        //En caso de introducir la mascara en decimal, se transforma de decimal a binario.
            String[] elementosMask = mascara.split("\\.");
            for (String octetoMask : elementosMask) {
                int oct = Integer.parseInt(octetoMask);
                String binario = Integer.toBinaryString(oct) + ".";
                while (binario.length() != 9) {
                    binario = 0 + binario;
                }
                mascarabinaria += binario;
            }
        }

        mascarabinaria = mascarabinaria.replace(".","");        //Se quitan los puntos para futuras operaciones.

        if(opcionmasc.equals("1")){
           broadcast = fn.dirBC(resultadoIPBIN, mascara);           //Funcion para conseguir direccion de broadcast si se ha introducido mascara mediante CIDR.
        }else if(opcionmasc.equals("2")){
           broadcast = fn.dirBCdec(resultadoIPBIN,mascarabinaria);         //Funcion para conseguir direccion de broadcast si se ha introducido mascara mediante Decimal.
        }

        broadcast = fn.insertarPunto(broadcast);                    //Se introducen los puntos para  usarlo en siguientes operaciones.

        String[] elementosbroadc = broadcast.split("\\.");    //Se transforma en decimal para presentarlo al usuario.
        for (String octetobc : elementosbroadc) {
            int decimalbc = fn.bitodecConv(octetobc);
            String direccdeb = Integer.toString(decimalbc) + ".";
            resultadobc = resultadobc + direccdeb;
        }
        resultadobc = resultadobc.substring(0,resultadobc.lastIndexOf("."));

        if(opcionmasc.equals("1")){
            red = fn.dirRD(resultadoIPBIN,mascara);                    //Funcion para conseguir direccion de red si se ha introducido mascara mediante CIDR.
        }else if(opcionmasc.equals("2")){
            red = fn.dirRDdec(resultadoIPBIN, mascarabinaria);                //Funcion para conseguir direccion de red si se ha introducido mascara mediante Decimal.
        }

        red = fn.insertarPunto(red);                                   //Se introducen los puntos para  usarlo en siguientes operaciones.

        String[] elementosRed = red.split("\\.");                //Se transforma a decimal para presentarlo al usuario.
        for(String octetoRD : elementosRed){
            int decimalRD = fn.bitodecConv(octetoRD);
            String direccRD = Integer.toString(decimalRD) + ".";
            resultadoRD = resultadoRD + direccRD;
        }

        resultadoRD = resultadoRD.substring(0,resultadoRD.lastIndexOf("."));

        int Hosts = 0;
        if(opcionmasc.equals("1")){     //Funcion para conseguir el numero de hosts disponibles si la mascara esta en formato CIDR.
            Hosts = fn.calculaNumeroHosts(mascara);
        }else if(opcionmasc.equals("2")){
            Hosts = fn.calcularNumeroHostDec(mascarabinaria);       //Funcion para conseguir el numero de hosts disponibles si la mascara esta en formato decimal.
        }

        String claseIP = fn.clasificadorIP(ip);                        //Calcula clase de IP.

        System.out.println("IP: " + ip);
        System.out.println("Direccion de red: " + resultadoRD);
        System.out.println("Direccion de broadcast: " + resultadobc);
        System.out.println("Numero de hosts disponible: " + Hosts);
        System.out.println("El rango de direcciones es: " + fn.rango(resultadoRD,resultadobc));
        System.out.println("Tipo de IP: " + claseIP);
    }
}