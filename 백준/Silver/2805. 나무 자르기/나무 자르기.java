
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int numTree, needTree;
    static int[] trees;
    static int min = 0, max = 0, maxHeight = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(br.readLine());

        numTree = Integer.parseInt(token.nextToken());
        needTree = Integer.parseInt(token.nextToken());
        trees = new int[numTree];

        token = new StringTokenizer(br.readLine());

        for (int i = 0; i < numTree; i++) {
            trees[i] = Integer.parseInt(token.nextToken());
            if (trees[i] > max) max = trees[i];
        }
//        System.out.printf("min: %d , max: %d\n", min, max);
        System.out.println(binarySearch());
    }

    static int binarySearch () {

        while (true) {
            double sumTree = 0; int height = (min + max) / 2;

//            System.out.println("height is " + height);

            for (int i = 0; i < numTree; i++) {
                if (trees[i] > height) sumTree += (trees[i] - height);

                if (sumTree > needTree) {
                    // 높이를 더 올려도 되는 경우
                    if (maxHeight < height) maxHeight = height;
                    min = height + 1;
                    break;
                }
            }

            if (sumTree < needTree) {
                // 높이를 내려야 하는 경우
                max = height - 1;
            } else if (sumTree == needTree) {
                return height;
            }


            if (max < min) return maxHeight;
            // todo: min 3, max 4 경합이 이루어지는 경우
            // min이 3이다 -> 3 위치에서는 목표치보다 양이 많아서 올려도 된다
            // max가 4이다 -> 4 위치에서는 목표치보다 양이 부족하다
            // 이때 height는 3이 되고 올려야 하므로 min이 또 3이 돼서 무한 반복... => 3이 최선이므로 이때는 3 (maxHeight) 리턴하도록..
            // if 조건을 min >= max 에서 max - min <= 1 로 변경
        }
    }
}