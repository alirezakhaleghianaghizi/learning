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
        tabel.calculateMaximumHigh();
        tabel.Gaiming(n);
        if (tabel.min[0]>tabel.h) System.out.print("GAME OVER");
        else System.out.print(tabel.min[0]);

    }
}
/*class Tabel {

    int w;
    int h;
    boolean [][]blooks;
    int []min=new int[1];
    char []ajor;
    int maxHigh[]={0};
    int maxWide[]={0};
    void calculateMaximumHigh(){
        for (int i = 0; i < ajor.length; i++) {
            switch (ajor[i]){
                case 'S':
                    maxWide[0]+=3;
                    maxHigh[0]+=1;
                    ;break;
                case 'P':
                    maxWide[0]+=1;
                    maxHigh[0]+=3;
                    ;break;
                case 'L':
                    maxWide[0]+=2;
                    maxHigh[0]+=3;
                    ;break;
                case 'X':
                    maxWide[0]+=2;
                    maxHigh[0]+=2;
                    ;break;
                case 'T':
                    maxWide[0]+=3;
                    maxHigh[0]+=2;
                    ;break;
                case 'Z':
                    maxWide[0]+=3;
                    maxHigh[0]+=2;
                    ;break;
            }
        }

    }
    void ajorLenth (int n){
        ajor=new char[n];
    }
    void blookLenth (){
        blooks=new boolean[h][w];
    }
    void mini(){
        min[0]=h+1;
    }
    boolean gameOver[]={false};
    boolean fillS(int i,int j){
        if(j>w-3){ return  false; }
        else if(blooks[i][j]==true||blooks[i][j+1]==true||blooks[i][j+2]==true){return  false;}
        else {
            for (int k = 0; k < i; k++) {
                for (int l = j; l <j+3 ; l++) {
                    if(blooks[k][l]==true){
                        return false;
                    }
                }
            }

        }
        return true;
    }
    boolean fillP(int i,int j){
        if(i<3-1){ return  false; }
        else {
            for (int k = 0; k <= i; k++) {
                if(blooks[k][j]==true){
                    return false;
                }
            }
        }
        return true;
    }
    boolean fillL(int i,int j){
        if(j>w-2||i<3-1){ return  false; }
        else {
            for (int k = 0; k <= i; k++) {
                for (int l = j; l <j+2 ; l++) {
                    if(blooks[k][l]==true){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    boolean fillX(int i,int j){
        if(j>w-2||i<1){ return  false; }
        else {
            for (int k = 0; k <= i; k++) {
                for (int l = j; l <j+2 ; l++) {
                    if(blooks[k][l]==true){
                        return false;
                    }
                }
            }

        }
        return true;
    }
    boolean fillT(int i,int j){
        if(j>w-3||i>h-2){ return  false; }
        else if(blooks[i][j]==true||blooks[i][j+1]==true||blooks[i][j+2]==true||blooks[i+1][j+1]==true){return  false;}
        else {
            for (int k = 0; k < i; k++) {
                for (int l = j; l <j+3 ; l++) {
                    if(blooks[k][l]==true){
                        return false;
                    }
                }
            }

        }
        return true;
    }
    boolean fillZ(int i,int j){
        if(j>w-3||i>h-2){
            return  false;
        }

        else if(blooks[i][j]==true||blooks[i][j+1]==true||blooks[i+1][j+2]==true||blooks[i+1][j+1]==true){
            return  false;}
        else {

            for (int k = 0; k <= i; k++) {
                for (int l = j; l <j+3 ; l++) {
                    if(blooks[k][l]==true){
                        return false;
                    }
                }
            }

        }
        return true;
    }
    void Gaiming(int n){
        if(n==0){
            int high=Integer.MAX_VALUE;
            boolean high1=false;
            int ertefa=0;
            if (h>maxHigh[0]){
                ertefa=maxHigh[0]-1;
            }
            for (int i = ertefa; i <h; i++) {
                for (int j = 0; j<maxWide[0]&&j<w; j++) {
                    if(blooks[i][j]==true){
                        high=h-i;
                        high1=true;
                        break;

                    }
                }
                if (high1==true)break;
            }
            if (min[0]>high) min[0]=high;
        }
        else if(n>0) {


            switch (ajor[n-1]){
                case 'S':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0  ; i--) {
                        boolean isFeat=false;
                        for (int j = 0; j<=maxWide[0]-1&&j<w; j++) {
                            if(fillS(i,j)){
                                blooks[i][j]=true;
                                blooks[i][j+1]=true;
                                blooks[i][j+2]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i][j+1]=false;
                                blooks[i][j+2]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;
                case 'P':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0  ; i--) {
                        boolean isFeat=false;
                        for (int j = 0; j<=maxWide[0]-1&&j<w; j++) {
                            if(fillP(i,j)){
                                blooks[i][j]=true;
                                blooks[i-1][j]=true;
                                blooks[i-2][j]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i-1][j]=false;
                                blooks[i-2][j]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;
                case 'L':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0  ; i--) {
                        boolean isFeat=false;
                        for (int j = 0;j<=maxWide[0]-1&&j<w; j++) {
                            if(fillL(i,j)){
                                blooks[i][j]=true;
                                blooks[i-1][j]=true;
                                blooks[i-2][j]=true;
                                blooks[i][j+1]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i-1][j]=false;
                                blooks[i-2][j]=false;
                                blooks[i][j+1]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;
                case 'X':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0  ; i--) {
                        boolean isFeat=false;
                        for (int j = 0; j<=maxWide[0]-1&&j<w; j++) {
                            if(fillX(i,j)){
                                blooks[i][j]=true;
                                blooks[i][j+1]=true;
                                blooks[i-1][j]=true;
                                blooks[i-1][j+1]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i][j+1]=false;
                                blooks[i-1][j]=false;
                                blooks[i-1][j+1]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;
                case 'T':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0   ; i--) {
                        boolean isFeat=false;
                        for (int j = 0; j<=maxWide[0]-1&&j<w; j++) {
                            if(fillT(i,j)){
                                blooks[i][j]=true;
                                blooks[i][j+1]=true;
                                blooks[i][j+2]=true;
                                blooks[i+1][j+1]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i][j+1]=false;
                                blooks[i][j+2]=false;
                                blooks[i+1][j+1]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;
                case 'Z':

                    for (int i = h-1; i>=h-maxHigh[0]-1 &&i>=0 ; i--) {
                        boolean isFeat=false;
                        for (int j = 0; j<=maxWide[0]-1&&j<w; j++) {
                            if(fillZ(i,j)){
                                blooks[i][j]=true;
                                blooks[i][j+1]=true;
                                blooks[i+1][j+1]=true;
                                blooks[i+1][j+2]=true;
                                Gaiming(n-1);
                                blooks[i][j]=false;
                                blooks[i][j+1]=false;
                                blooks[i+1][j+1]=false;
                                blooks[i+1][j+2]=false;
                                isFeat=true;
                            }
                        }
                        if (isFeat==true){
                            break;
                        }
                    }

                    ;break;











            }
        }






    }
}*/
