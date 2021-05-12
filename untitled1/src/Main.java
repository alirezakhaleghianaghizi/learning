import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int arzeroodkhaneh = scan.nextInt();
        int n = scan.nextInt();
        Stone stones = new Stone(n);
        for (int i = 0; i < n; i++) {
            stones.x[i] = scan.nextInt();
            stones.y[i] = scan.nextInt();
            stones.radious[i] = scan.nextInt();
            stones.visit[i] = false;
        }
        stones.sort();
        boolean rechingBeach[] = {false};
        int placeI = 0;
        if (Math.pow(stones.x[0], 2) + Math.pow(stones.y[0], 2) > Math.pow(stones.radious[0], 2)) {
            for (int i = placeI + 1; i < n; i++) {
                if (Math.pow(stones.x[0], 2) + Math.pow(stones.y[0], 2) <= Math.pow(stones.radious[0], 2)) {
                    placeI = i;
                    break;
                }
            }
            if (placeI == 0) rechingBeach[0] = false;
        }
        int[] tedadHarakat = new int[1];
        int[] minStone = new int[1];
        minStone[0] = 100000;
        tedadHarakat[0] = 0;
        //stones.visit[placeI]=true;
        stones.recursive(placeI, arzeroodkhaneh, tedadHarakat, minStone,rechingBeach);
        //System.out.println("");
        if (minStone[0] == 100000 || rechingBeach[0] == false) System.out.print("0");
        else System.out.print(minStone[0]);


    }


}/*
class Stone {

    int x[];
    int y[];
    int radious[];
    boolean visit[];

    Stone(int n) {
        x = new int[n];
        y = new int[n];
        radious = new int[n];
        visit = new boolean[n];
    }

    void scan() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < x.length; i++) {
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
            radious[i] = scan.nextInt();
        }

    }

    void sort() {

        int i, j, temp;
        boolean swapped;
        for (i = 0; i < x.length - 1; i++) {
            swapped = false;
            for (j = 0; j < x.length - i - 1; j++) {
                if (y[j] > y[j + 1]) {
                    temp = y[j];
                    y[j] = y[j + 1];
                    y[j + 1] = temp;
                    temp = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = temp;
                    temp = radious[j];
                    radious[j] = radious[j + 1];
                    radious[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
    }

    void recursive(int placeI, int arzeRodkhane, int[] tedadHarekat, int[] minStone,boolean []reaching ) {
        if (y[placeI] >= arzeRodkhane - radious[placeI]) {
            reaching[0]=true;
            tedadHarekat[0]++;
            if (tedadHarekat[0] < minStone[0]) minStone[0] = tedadHarekat[0];
            tedadHarekat[0]--;
        } else if (placeI < x.length - 1) {
            for (int i = 0; i < x.length ; i++) {
                if (i != placeI&&visit[i] != true) {

                    if (Math.pow(x[i], 2) + Math.pow(y[i], 2) <= Math.pow(radious[i], 2)) {
                        visit[i] = true;
                        int komakiTedad = tedadHarekat[0];
                        tedadHarekat[0] = 0;
                        recursive(i, arzeRodkhane, tedadHarekat, minStone,reaching);
                        tedadHarekat[0] = komakiTedad;
                        visit[i] = false;
                    } else if (Math.pow(y[i] - y[placeI], 2) + Math.pow(x[placeI] - x[i], 2) <= Math.pow(radious[i] + radious[placeI], 2)) {
                        visit[i] = true;
                        tedadHarekat[0]++;
                        recursive(i, arzeRodkhane, tedadHarekat, minStone,reaching);
                        tedadHarekat[0]--;
                        visit[i] = false;
                    }
                }
            }


        }
    }


}
*/