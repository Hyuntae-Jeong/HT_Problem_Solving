import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Main.solve();
    }

    public static StringTokenizer tokenize(String input) {
        if (input.equals("0 0 0")) return null;
        return new StringTokenizer(input);
    }

    public static Boolean evaluate(int a, int b, int c) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a); list.add(b); list.add(c);

        Integer max = Math.max(a, Math.max(b, c));
        list.remove(max);

        return max * max == list.get(0) * list.get(0) + list.get(1) * list.get(1);
    }

    public static void solve() throws IOException {

        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer;

        while ( ( tokenizer = tokenize(br.readLine()) ) != null ) {

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            if(evaluate(a, b, c)) sb.append("right\n");
            else sb.append("wrong\n");
        }

        System.out.println(sb);
    }
}