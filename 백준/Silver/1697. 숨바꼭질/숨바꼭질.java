import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
    static int N, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] visited = new int[100_001];

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        int step = goFindSister();
        System.out.println(step);
    }

    public static int goFindSister() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        if (N == K) return 0;

        int pos = N;
        queue.add(pos);
        visited[pos] = 1;

        while (!queue.isEmpty()) {
            pos = queue.poll();

            // x - 1 이동
            if (pos - 1 >= 0 && visited[pos - 1] == 0){
                if(pos - 1 == K) return visited[pos];
                visited[pos - 1] = visited[pos] + 1;
                queue.add(pos - 1);
            }

            // 수빈이가 이미 동생을 지나쳤다면 더 멀리 갈 필요는 없다
            if (pos < K) {
                // x + 1 이동
                if (pos + 1 <= 100_000 && visited[pos + 1] == 0){
                    if(pos + 1 == K) return visited[pos];
                    visited[pos + 1] = visited[pos] + 1;
                    queue.add(pos + 1);
                }

                // x * 2 이동  (여기서 캐치해야할 조건: x*2면 뒤로는 못가니까!!)
                if (pos * 2 <= 100_000 && visited[pos * 2] == 0){
                    if(pos * 2 == K) return visited[pos];
                    visited[pos * 2] = visited[pos] + 1;
                    queue.add(pos * 2);
                }
            }
        }

        return 0;
    }
}