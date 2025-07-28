import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N;

    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        startProcess();
    }

    static Stack<Integer> stack = new Stack<>();
    static String command;

    public static void startProcess() throws IOException {
        StringBuilder sb = new StringBuilder();

        while (N > 0){
            token = new StringTokenizer(br.readLine());
            command = token.nextToken();

            switch (command) {
                case "push":
                    stack.push(Integer.parseInt(token.nextToken()));
                    break;
                case "pop":
                    sb.append(stack.empty() ? -1 : stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.empty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    sb.append(stack.empty() ? -1 : stack.peek()).append("\n");
                    break;
            }

            N--;
        }
        System.out.println(sb);
    }
}