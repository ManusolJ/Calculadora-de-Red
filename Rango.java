public class Rango {
    public static void main(String[] args) {
        String broadcast = "34.78.124.255";
        String red = "34.78.124.0";

        int a = Integer.parseInt(red.substring(broadcast.lastIndexOf(".") + 1, red.length())) + 1;
        String primdir = red.substring(0,red.lastIndexOf(".")+1) + a;
        System.out.println("Primera direcccion: " + primdir);

        int i = Integer.parseInt(broadcast.substring(broadcast.lastIndexOf(".") + 1, broadcast.length())) - 1;
        String ultdir = broadcast.substring(0,broadcast.lastIndexOf(".")+1) + i;
        System.out.println("Ultima direccion: " + ultdir);
    }
}
