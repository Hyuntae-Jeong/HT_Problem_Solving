import java.io.*;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int R = 1, G = 2, B = 3;
    static int[][] colorInfos;
    static int[][] minCost;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        minCost = new int[N+1][4];

        getInput();
    }

    static void getInput() throws IOException {
        for (int cost, i = 1; i <= N; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());

            /* R */ cost = Integer.parseInt(token.nextToken());
            minCost[i][R] = Math.min(minCost[i - 1][G], minCost[i - 1][B]) +  cost;

            /* G */ cost = Integer.parseInt(token.nextToken());
            minCost[i][G] = Math.min(minCost[i - 1][R], minCost[i - 1][B]) + cost;

            /* B */ cost = Integer.parseInt(token.nextToken());;
            minCost[i][B] = Math.min(minCost[i - 1][R], minCost[i - 1][G]) + cost;

        }

        System.out.print(Math.min(Math.min(minCost[N][R], minCost[N][G]), minCost[N][B]));
    }
}