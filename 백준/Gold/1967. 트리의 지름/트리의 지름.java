import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ChildInfo {
    int childIndex;
    int length;
    int max;

    ChildInfo (int childIndex, int length) {
        this.childIndex = childIndex;
        this.length = length;
    }
}

class Node {
    int value;
    ArrayList<ChildInfo> childInfos;
    int maxChildLength;
    int secondMaxChildLength;

    Node (int value, int child, int length) {
        this.value = value;
        this.childInfos = new ArrayList<>();
        this.childInfos.add(new ChildInfo(child, length));
    }

    void addChildInfo(int child, int length) {
        this.childInfos.add(new ChildInfo(child, length));
    }
}

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static Node[] nodes;
    static int totalMax = 0;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];

        getInput();
        getMaxLength();
        System.out.print(totalMax);
    }

    static void getInput() throws IOException {
        int parentNode, childNode, length;

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            parentNode = Integer.parseInt(token.nextToken());
            childNode = Integer.parseInt(token.nextToken());
            length = Integer.parseInt(token.nextToken());

            if (nodes[parentNode] == null) {
                nodes[parentNode] = new Node(parentNode, childNode, length);
            } else {
                nodes[parentNode].addChildInfo(childNode, length);
            }
        }
    }

    static void getMaxLength() {
        // Head에서부터 후위 순회하면서 left max, right max를 구한다
        gotoChild(1);
    }


    static void gotoChild (int parent) {
        if (nodes[parent] == null) return;

        for (ChildInfo child : nodes[parent].childInfos) {
            gotoChild(child.childIndex);

            if (nodes[child.childIndex] == null) {
                child.max = child.length;
            } else {
                child.max = nodes[child.childIndex].maxChildLength + child.length;
            }

            if (child.max > nodes[parent].maxChildLength) {
                nodes[parent].secondMaxChildLength = nodes[parent].maxChildLength;
                nodes[parent].maxChildLength = child.max;
            }
            else if (child.max > nodes[parent].secondMaxChildLength) nodes[parent].secondMaxChildLength = child.max;
        }

        totalMax = Math.max(totalMax, nodes[parent].maxChildLength + nodes[parent].secondMaxChildLength);

        if (debug) {
            System.out.printf("parent: %d , firstMax: %d, secondMax: %d\n", parent, nodes[parent].maxChildLength, nodes[parent].secondMaxChildLength);

            for (ChildInfo child: nodes[parent].childInfos) {
                System.out.printf("child(%d) : %d\n", child.childIndex, child.max);
            }

            System.out.println();
        }
    }
}