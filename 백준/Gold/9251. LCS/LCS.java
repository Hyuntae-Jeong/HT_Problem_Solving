
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] first, second;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        String firstWord = br.readLine();
        String secondWord = br.readLine();

//        firstWord.toCharArray() 대신 내부 구현된 내용을 변형하여 시작 index를 1부터 시작하도록 커스터마이징함
        first = new char[firstWord.length() + 1];
        System.arraycopy(firstWord.toCharArray(), 0, first, 1, firstWord.length());

        second = new char[secondWord.length() + 1];
        System.arraycopy(secondWord.toCharArray(), 0, second, 1, secondWord.length());

        startProcess(firstWord.length(), secondWord.length()); 
    }

    static int[][] lcsGraph;
    static void startProcess(int firstWordLength, int secondWordLength) {
        lcsGraph = new int[firstWordLength + 1][secondWordLength + 1];
        for (int i = 1; i <= firstWordLength; i++) {
            for (int j = 1; j <= secondWordLength; j++) {
                if (first[i] == second[j]) {
                    lcsGraph[i][j] = lcsGraph[i - 1][j - 1] + 1;
                } else {
                    lcsGraph[i][j] = Math.max(lcsGraph[i-1][j], lcsGraph[i][j-1]);
                }
                if (debug) System.out.printf("%d\t", lcsGraph[i][j]);
            }
            if (debug) System.out.println();
        }
        System.out.println(lcsGraph[firstWordLength][secondWordLength]);
    }
}