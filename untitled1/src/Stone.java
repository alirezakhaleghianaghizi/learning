import java.util.Scanner;

public class Stone {

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
