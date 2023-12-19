import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PruebaPatrones {
    public static void main(String[] args) {
        String cadena = "/9";
        //Pattern pat = Pattern.compile("[a-zA-Z]{5,10}");
        //Matcher mat = pat.matcher(cadena);
        /*if (cadena.matches("^/[0-2]?[0-9]")){ //[0-2]\?[0-9]
            System.out.println("SI");
        }else
            System.out.println("NO");*/
        Scanner sc = new Scanner(System.in);
        String mascarad = "";
        int [] nummasc = {0,128,192,224,240,248,252,254,255};
        try{
            System.out.print("Escribe la máscara con notación decimal: ");
            mascarad = sc.next();

            String [] mascElementos = mascarad.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
            boolean resultado = true;
            boolean es0 = true;
            /*for(String oct : mascElementos){
                int mascbyte = Integer.parseInt(oct);
                if (mascbyte < 0 || mascbyte > 255 && resultado){
                    resultado = false;
                }else if (mascbyte != 255 && resultado){
                    for (int cont = 0; cont < nummasc.length; cont++){
                        if (mascbyte == nummasc[cont]){
                            resultado = true;
                            cont = nummasc.length;
                        }else
                            resultado = false;
                    }
                }*/

                for(int pricont = 0; pricont<mascElementos.length; pricont++){
                    int mascbyte = Integer.parseInt(mascElementos[pricont]);
                    if (mascbyte < 0 || mascbyte > 255 && resultado){
                        resultado = false;
                    }else if (mascbyte != 255 && resultado){
                        for (int cont = 0; cont < nummasc.length; cont++){
                            if (mascbyte == nummasc[cont]) {
                                resultado = true;
                                cont = nummasc.length;
                            }else
                                resultado = false;
                        }
                    }
        }
            if (!resultado || Integer.parseInt(mascarad.substring(mascarad.lastIndexOf(".")+1,mascarad.length())) > 255)//Quitar la 2º condicion
                throw new Exception("Error al introducir la máscara con notación decimal. Prueba de nuevo.");
            System.out.println("Máscara en decimal correcta.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        }
    }
}
