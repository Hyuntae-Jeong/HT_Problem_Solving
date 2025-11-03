import java.io.*;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] path;
    static boolean debug = false;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        path = new int[N + 1][N + 1];

        getInput();
        if (debug) printPath();

        floydWarshall();
        printPath();
    }

    static void getInput() throws IOException {
        int s, e, v;
        for (int i = 0; i < M; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());
            v = Integer.parseInt(token.nextToken());
            if (path[s][e] != 0) path[s][e] = Math.min(path[s][e], v);
            else path[s][e] = v;
        }
    }

    static void printPath() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.printf("%d ", path[i][j]);
            }
            System.out.println();
        }
    }

    static void floydWarshall() {
        for (int stopover = 1; stopover <= N; stopover++) {
            for (int s = 1; s <= N; s++) {
                if (stopover == s || path[s][stopover] == 0) continue;
                for (int e = 1; e <= N; e++) {
                    if (s == e || e == stopover) continue;
                    if (path[s][stopover] == 0 || path[stopover][e] == 0) continue;

                    if (path[s][e] == 0) {
                        path[s][e] = path[s][stopover] + path[stopover][e];
                    } else {
                        path[s][e] = Math.min(path[s][e], path[s][stopover] + path[stopover][e]);
                    }
                }
            }
            if (debug) printPath();
        }
    }
}