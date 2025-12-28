import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Data {
    int priority;
    boolean target;

    Data (int priority, boolean target) {
        this.priority = priority;
        this.target = target;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.priority).append(" | ");
        if (target) {
            sb.append("TRUE");
        } else {
            sb.append("FALSE");
        }
        return sb.toString();
    }
}

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int TEST_CASE = Integer.parseInt(br.readLine());

        for (int i = 0; i < TEST_CASE; i++) {
            doProcess();
        }
    }

    static void doProcess() throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int targetIndex = Integer.parseInt(token.nextToken());

        Queue<Data> queue = new ArrayDeque<>(N);
        token = new StringTokenizer(br.readLine());
        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(token.nextToken());
            boolean flag = i == targetIndex;
            values[i] = value;
            queue.add(new Data(value, flag));
        }

        Arrays.sort(values);

        int cnt = 0, largestIndex = values.length - 1;
        while (!queue.isEmpty()) {
            Data data = queue.poll();

            // 빼도 되는 data인지 체크
            if (values[largestIndex] == data.priority) {
                largestIndex--;
                cnt++;

                // target 인지 체크
                if (data.target) {
                    System.out.println(cnt);
                    return;
                }
            } else {
                queue.add(data);
            }
        }
    }
}