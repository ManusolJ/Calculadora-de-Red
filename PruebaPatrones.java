import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebaPatrones {
    public static void main(String[] args) {
        String cadena = "/9";
        //Pattern pat = Pattern.compile("[a-zA-Z]{5,10}");
        //Matcher mat = pat.matcher(cadena);
        if (cadena.matches("^/[0-2]?[0-9]")){ //[0-2]\?[0-9]
            System.out.println("SI");
        }else
            System.out.println("NO");
    }
}
