import java.io.*;
import java.util.HashMap;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Long, Long> savedFibo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        savedFibo.put(0L, 0L);
        savedFibo.put(1L, 1L);
        savedFibo.put(2L, 1L);

        long answer = fibo(Long.parseLong(br.readLine()));
        System.out.println(answer);
    }

    static long fibo(long n) {
        if (savedFibo.containsKey(n)) return savedFibo.get(n);
        long halfUp = (n + 1) / 2, half = n / 2;
        savedFibo.put(n, (fibo(halfUp) * fibo(n + 1 - halfUp) + fibo(half) * fibo(n - 1 - half)) % 1_000_000_007);
//        System.out.printf("f(%d) = f(%d)*f(%d) + f(%d)*f(%d) = %d\n", n, halfUp, n+1-halfUp, half, n-1-half, savedFibo.get(n));
        return savedFibo.get(n);
    }
}