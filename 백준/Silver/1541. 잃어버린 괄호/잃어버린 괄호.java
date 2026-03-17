
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        char[] inputChar = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        boolean isMinusOccurred = false;
        long total = 0;
        for (char c : inputChar) {
            if (c != '+' && c != '-') {
                sb.append(c);
                continue;
            }
            total = isMinusOccurred ? total - Long.parseLong(sb.toString()) : total + Long.parseLong(sb.toString());
            if (c == '-') isMinusOccurred = true;
            sb = new StringBuilder();
        }
        total = isMinusOccurred ? total - Long.parseLong(sb.toString()) : total + Long.parseLong(sb.toString());

        System.out.print(total);
    }
}