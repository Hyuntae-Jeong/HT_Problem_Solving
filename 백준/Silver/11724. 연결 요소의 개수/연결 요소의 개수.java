import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] node, leaderNode;
    static int leaderCount = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        node = new int[N + 1];
        leaderNode = new int[N + 1];

        startProcess();
        getAnswer();

    }

    static void startProcess() throws IOException {
        StringTokenizer token;
        int a, b;

        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());

//            System.out.println(a + " <---> " + b);

            if (node[a] == node[b]) {
                if (node[a] == 0) {
                    leaderNode[++leaderCount] = a;
                    node[a] = leaderCount;
                    node[b] = leaderCount;
                }
            } else if (node[a] == 0) {
                node[a] = node[b];
            } else if (node[b] == 0) {
                node[b] = node[a];
            } else {
                // a != b && a != 0 && b != 0
                // compare leader
                if (leaderNode[node[a]] != leaderNode[node[b]]) {
                    leaderNode[node[b]] = leaderNode[node[a]];
                }
            }

//            System.out.println("node info");
//            for (int j = 1; j <= N; j++) System.out.printf("%d : %d\n", j, node[j]);
//
//            System.out.println("leader info");
//            for (int j = 1; j <= leaderCount; j++) System.out.printf("%d -> %d\n", j, leaderNode[j]);
//            System.out.println();
        }
    }

    static void getAnswer() {
        boolean[] answer = new boolean[N + 1];
        int count = 0;

        for (int i = 1; i <= leaderCount; i++) {
            if (!answer[leaderNode[i]]) {
                answer[leaderNode[i]] = true;
                count++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (node[i] == 0) count++;
        }

        System.out.println(count);

    }
}