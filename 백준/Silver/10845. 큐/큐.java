import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N;
    static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        startProcess();

    }

    static String command;
    static Integer value;
    static StringBuilder sb = new StringBuilder();

    public static void startProcess() throws IOException {
        while (N > 0) {
            token = new StringTokenizer(br.readLine());
            command = token.nextToken();
            switch (command) {
                case "push":
                    value = Integer.parseInt(token.nextToken());
                    queue.offer(value);
                    break;
                case "pop":
                    value = queue.poll();
                    if (value == null) sb.append("-1\n");
                    else sb.append(value).append("\n");
                    break;
                case "front":
                    value = queue.peekFirst();
                    if (value == null) sb.append("-1\n");
                    else sb.append(value).append("\n");
                    break;
                case "back":
                    value = queue.peekLast();
                    if (value == null) sb.append("-1\n");
                    else sb.append(value).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    if(queue.isEmpty()) sb.append("1\n");
                    else sb.append("0\n");
            }
            N--;
        }
        System.out.println(sb);
    }
}