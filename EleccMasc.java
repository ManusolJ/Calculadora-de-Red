import java.util.Scanner;
import java.util.regex.Pattern;

public class EleccMasc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionmasc = 0;
        while (!String.valueOf(opcionmasc).matches("[1-2]")) {
            try{//Elegir la notacion de la máscara
                System.out.println("¿Qué formato de máscara quieres?\n1. Notación CDIR\n2. Notación decimal");
                opcionmasc = sc.nextInt();
                if (!String.valueOf(opcionmasc).matches("[1-2]")) throw new Exception("Error, opción incorrecta. Vuelve a intentarlo.");

            }catch (Exception e){
                //System.out.println("Error, opción incorrecta.");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            switch (opcionmasc) {
                case 1://Notacion CDIR
                    String mascaracdir = "0";
                    System.out.println("Notación CDIR elegida.");
                    while (!mascaracdir.matches("^/[0-3]?[0-9]") || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) > 32 || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) <= 0) {//Hasta que no sea correcta
                        try {
                            System.out.print("Escribe la máscara con notación CDIR: ");
                            mascaracdir = sc.next();
                            if (!mascaracdir.matches("^/[0-3]?[0-9]") || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) > 32 || Integer.parseInt(mascaracdir.substring(1, mascaracdir.length())) <= 0)//Comprueba que este entre 1 y 32
                                throw new Exception("Error al introducir la máscara con notación CDIR. Prueba de nuevo.");
                            System.out.println("Máscara CDIR correcta.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;
                case 2://Notacion decimal
                    String mascarad;
                    System.out.println("Notación decimal elegida.");
                    boolean repite = true;
                    while (repite) {//Hasta que no sea correcta
                        try{
                            System.out.print("Escribe la máscara con notación decimal: ");
                            mascarad = sc.next();
                            int [] nummasc = {0,128,192,224,240,248,252,254};//Bytes de máscara posibles cuando no es 255
                            String [] mascElementos = mascarad.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
                            boolean resultado = true;
                            boolean es0 = true;
                            for(int pricont = 0; pricont<mascElementos.length; pricont++){
                                int mascbyte = Integer.parseInt(mascElementos[pricont]);
                                if (mascbyte < 0 || mascbyte > 255 || Integer.parseInt(mascarad.substring(0,mascarad.indexOf("."))) == 0 && resultado){//Comprueba si el primer número esta entre el 0 y el 255 y comprueba que el primer byte no sea 0.
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

                            if (!resultado)//Devuelve el error si no es válido.
                                throw new Exception("Error al introducir la máscara con notación decimal. Prueba de nuevo.");
                            System.out.println("Máscara en decimal correcta.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }
                    }
            }
        }
    }
}
