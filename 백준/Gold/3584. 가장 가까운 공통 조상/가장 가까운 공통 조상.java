import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int parent;
        int depth;

        public Node() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            Node[] tree = new Node[n + 1];
            for (int i = 1; i <= n; i++)
                tree[i] = new Node();

            for (int i = 1; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[b].parent = a;
            }

            for (int now = 1; now <= n; now++)
                if (tree[now].depth == 0)
                    setDepth(now, tree);


            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(findSameParent(a, b, tree)).append("\n");
        }

        System.out.println(sb);
    }

    private static int findSameParent(int a, int b, Node[] tree) {
        if (tree[a].depth > tree[b].depth) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (tree[a].depth != tree[b].depth) {
            b = tree[b].parent;
        }

        while (a != b) {
            a = tree[a].parent;
            b = tree[b].parent;
        }

        return a;
    }

    private static void setDepth(int now, Node[] tree) {
        if (tree[now].depth != 0)
            return;

        if (tree[now].parent == 0)
            return;

        setDepth(tree[now].parent, tree);

        tree[now].depth = tree[tree[now].parent].depth + 1;
    }
}