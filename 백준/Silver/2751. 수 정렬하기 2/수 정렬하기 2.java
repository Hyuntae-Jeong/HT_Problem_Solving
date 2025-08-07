import java.io.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[] negativeNumbers = new boolean[1000001];
    static boolean[] positiveNumbersAndZero = new boolean[1000001];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        getInputNumbers(N);
        printSortedNumbers();
    }

    public static void getInputNumbers(int N) throws IOException {
        int number;
        for (int i = 0; i < N; i++){
            number = Integer.parseInt(br.readLine());
            if (number < 0) negativeNumbers[-1 * number] = true;
            else positiveNumbersAndZero[number] = true;
        }
    }

    public static void printSortedNumbers() {
        for (int i = 1000000; i >= 1; i--) {
            if (negativeNumbers[i]) sb.append("-").append(i).append("\n");
        }

        for(int i = 0; i <= 1000000; i++) {
            if (positiveNumbersAndZero[i]) sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}