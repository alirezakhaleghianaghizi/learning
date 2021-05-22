import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int M;
        Scanner scanner = new Scanner(System.in);
        N=scanner.nextInt();
        M=scanner.nextInt();
        int run[]={0};
        int tedadRah[]={0};
        run(M,N,run,tedadRah);
        System.out.println(tedadRah[0]);
    }

     public static void run(int M, int N, int[] run, int[] tedadrah){
        if(run[0]==N){
            tedadrah[0]++;
        }
        else if(run[0]<N) {
            for (int i = 1; i <=M ; i++) {
                run[0]+=i;
                run(M,N,run,tedadrah);
                run[0]-=i;
            }
        }
    }
}
