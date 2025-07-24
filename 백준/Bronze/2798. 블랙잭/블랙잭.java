import java.io.*;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, cardCount, max = -1;
    static int[] cards;

    public static StringTokenizer tokenize(String input){
        return new StringTokenizer(input);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = tokenize(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        readCards();
        System.out.println(findBlackJack());
    }

    public static void readCards() throws IOException {
        StringTokenizer token = tokenize(br.readLine());
        cards = new int[N];
        int card;
        cardCount = 0;

        while (token.hasMoreTokens()) {
            card = Integer.parseInt(token.nextToken());
            if (card >= M) continue;
            cards[cardCount++] = card;
        }
    }

    public static int findBlackJack() {
        int i, j, k, sum;
        for (i = 0; i < cardCount - 2; i++) {
            sum = cards[i];
            for (j = i + 1; j < cardCount - 1; j++) {
                if (sum + cards[j] >= M) continue;
                sum += cards[j];

                for (k = j + 1; k < cardCount; k++) {
                    if (sum + cards[k] <= M && sum + cards[k] > max){
                        max = sum + cards[k];
                        if (max == M) return max;
                    }
                }

                sum -= cards[j];
            }
        }

        return max;
    }
}