import java.util.Scanner;
import java.util.regex.Pattern;

public class EleccMasc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionmasc;
        //while (opcionmasc)
        System.out.println("¿Qué formato de máscara quieres?\n1. Notación CDIR\n2. Notación decimal");
        opcionmasc = sc.nextInt();
        //Pattern pattern = Pattern.compile("^/[0-2]\\?[0-9]", Pattern.COMMENTS);
        switch (opcionmasc){
            case 1:
                String mascaracdir = "0";
                System.out.println("Notación CDIR elegida.");
                while (!mascaracdir.matches("^/[0-2]?[0-9]")){
                try{
                    System.out.print("Escribe la máscara con notación CDIR: ");
                    mascaracdir = sc.next();
                    if (!mascaracdir.matches("^/[0-2]?[0-9]")) throw new Exception("Error al introducir la máscara con notación CDIR. Prueba de nuevo.");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    //sc.nextLine();
                }
            }

                break;
            case 2:
                String mascarad = "0";
                System.out.println("Notación decimal elegida.");
                while (!mascarad.matches("^/[0-2]?[0-9]")){
                    try{
                        System.out.println("Escribe la máscara con notación decimal: ");
                        mascarad = sc.next();
                        if (!mascarad.matches("[0-3]?[0-9]")) throw new Exception("Error al introducir la máscara con notación CDIR. Prueba de nuevo.");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        //sc.nextLine();
                    }
                }

                break;
            default:
                System.out.println("Error, opción incorrecta. Vuelve");
        }
    }
}
