import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {


        Tabel tabel=new Tabel();

        tabel.w= parseInt(scanner.next());
        tabel.h= parseInt(scanner.next());
        int n= parseInt(scanner.next());
        tabel.ajorLenth(n);
        tabel.blookLenth();
        tabel.mini();
        for (int i = tabel.ajor.length-1; i >=0 ; i--) {
            tabel.ajor[i]=scanner.next().charAt(0);
        }
        tabel.Gaiming(n);
        if (tabel.min[0]>tabel.h) System.out.println("GAME OVER");
        else System.out.println(tabel.min[0]);

    }
}
