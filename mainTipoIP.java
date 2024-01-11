import java.util.Scanner;

public class mainTipoIP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*System.out.println("Introduce la IP: ");
        String ip = sc.nextLine();



        ClaseDeIP tipoip = new ClaseDeIP();
        System.out.println(tipoip.TipoIP(ip));*/

        String opcionmasc = "0";
        while (!opcionmasc.matches("[1-2]")) {//Opcion de la mascara
            try {
                System.out.println("¿Qué formato de máscara quieres?\n1. Notación CDIR\n2. Notación decimal");
                opcionmasc = sc.next();
                if (!opcionmasc.matches("[1-2]")) throw new Exception("Error, opción incorrecta. Vuelve a intentarlo.");

            } catch (Exception e) {
                //System.out.println("Error, opción incorrecta.");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        PruebaEleccMasc masc = new PruebaEleccMasc();
        System.out.println(masc.Mascara(opcionmasc));
    }
}
