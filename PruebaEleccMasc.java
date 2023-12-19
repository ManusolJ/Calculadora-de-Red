import java.util.Scanner;

public class PruebaEleccMasc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionmasc = 0;
        while (!String.valueOf(opcionmasc).matches("[1-2]")) {
            try{
                //opcionmasc = 0;
                System.out.println("¿Qué formato de máscara quieres?\n1. Notación CDIR\n2. Notación decimal");
                opcionmasc = sc.nextInt();
                if (!String.valueOf(opcionmasc).matches("[1-2]")) throw new Exception("Error, opción incorrecta. Vuelve a intentarlo.");

            }catch (Exception e){
                //System.out.println("Error, opción incorrecta.");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            switch (opcionmasc) {
                case 1:
                    String mascaracdir = "0";
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
                    String mascarad = "";
                    System.out.println("Notación decimal elegida.");
                    while (!mascarad.matches("[0-2]?[0-9]?[0-9]\\.")) {
                        try {
                            System.out.print("Escribe la máscara con notación decimal: ");
                            mascarad = sc.next();
                            String a = mascarad.substring(mascarad.lastIndexOf(".")+1,mascarad.length());
                            int b = Integer.parseInt(mascarad.substring(0,mascarad.indexOf(".")));
                            String [] mascElementos = mascarad.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
                            String resultado = "";
                            for(String oct : mascElementos){
                                int mascbyte = Integer.parseInt(oct);
                                if (mascbyte > 255){

                                }
                            }

                            System.out.println(b);
                            System.out.println(a);
                            if (!mascarad.matches("[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]") && Integer.parseInt(mascarad.substring(mascarad.lastIndexOf(".")+1,mascarad.length())) > 254)
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
