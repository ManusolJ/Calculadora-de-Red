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
        System.out.println("Introduce mascara: ");
        String mascarad = "";
        mascarad = sc.next();
        if (mascarad.matches("[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]\\.[0-2]?[0-9]?[0-9]")){
            System.out.println("correcta");
        }

        /*try{
            System.out.print("Escribe la máscara con notación decimal: ");
            mascarad = sc.next();
            int [] nummasc = {0,128,192,224,240,248,252,254};
            String [] mascElementos = mascarad.split("\\.");    //Se divide los numeros en el punto,introduciendolos en un array con 4 elementos.
            boolean resultado = true;
            boolean es0 = true;
                for(int pricont = 0; pricont<mascElementos.length; pricont++){
                    int mascbyte = Integer.parseInt(mascElementos[pricont]);
                    if (mascbyte < 0 || mascbyte > 255 && resultado){//Comprueba si el primer número esta entre el 0 y el 255
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
            if (!resultado || Integer.parseInt(mascarad.substring(0,mascarad.indexOf("."))) == 0)//Devuelve el error si no es válido y comprueba que el primer byte no sea 0.
                throw new Exception("Error al introducir la máscara con notación decimal. Prueba de nuevo.");
            System.out.println("Máscara en decimal correcta.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        }*/
    }
}
