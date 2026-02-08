
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long target;
    static int[] numbers;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        target = Long.parseLong(token.nextToken());
        numbers = new int[N];

        getInput();
        searchWithTwoPointer();
    }

    static void getInput() throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(token.nextToken());
        }
    }

    static void searchWithTwoPointer() {
        int s = 0, e = 0;
        double sum = numbers[0];
        int minLength = Integer.MAX_VALUE;

        // phase 1: end pointer not reached end
        while (e < N - 1) {
            if (debug) {
                System.out.println("[B4] s: " + s + ", e: " + e + ", sum: " + sum + ", minLength: " + minLength);
            }

            if (sum < target) {
                sum += numbers[++e];
            } else {
                minLength = Math.min(minLength, e - s + 1);

                if (s == e) {
                    // 예외 1: 1개의 숫자가 target보다 커서 s가 e보다 커지는 경우 예방
                    sum += numbers[++e];
                } else {
                    sum -= numbers[s++];
                }
            }

            if (debug) {
                System.out.println("[AF] s: " + s + ", e: " + e + ", sum: " + sum + ", minLength: " + minLength);
                System.out.println();
            }
        }

        while (s < N && sum >= target) {
            if (debug) {
                System.out.println("> [B4] s: " + s + ", e: " + e + ", sum: " + sum + ", minLength: " + minLength);
            }

            minLength = Math.min(minLength, e - s + 1);
            sum -= numbers[s++];

            if (debug) {
                System.out.println("> [AF] s: " + s + ", e: " + e + ", sum: " + sum + ", minLength: " + minLength);
                System.out.println();
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            System.out.print(0);
        } else {
            System.out.println(minLength);
        }
    }
}