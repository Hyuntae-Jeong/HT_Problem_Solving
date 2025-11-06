
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] remainder = new boolean[42];

        int number, count = 0;
        for (int i = 0; i < 10; i++) {
            number = Integer.parseInt(br.readLine());
            if (!remainder[number % 42]){
                remainder[number % 42] = true;
                count++;
            }
        }
        System.out.print(count);
    }
}