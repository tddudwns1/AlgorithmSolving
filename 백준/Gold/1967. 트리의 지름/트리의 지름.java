import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Tree {
    int number;
    int weight;
    int parent;
    List<Tree> childs = new ArrayList<>();

    public Tree(int number) {
        this.number = number;
    }
}

public class Main {
    static int answer = 0;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree[] tree = new Tree[n + 1];

        for (int i = 1; i <= n; i++)
            tree[i] = new Tree(i);

        tree[1].parent = 1;
        tree[1].weight = 0;

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[child].weight = weight;
            tree[child].parent = parent;
            tree[parent].childs.add(tree[child]);
        }

        for (int i = 1; i <= n; i++) {
            if (!tree[i].childs.isEmpty())
                continue;

            checked = new boolean[n + 1];
            checked[i] = true;
            dfs(tree, tree[i].parent, tree[i].weight);
        }

        System.out.println(answer);
    }

    private static void dfs(Tree[] tree, int now, int sum) {
        if (checked[now])
            return;
        checked[now] = true;

        answer = Math.max(answer, sum);

        for (Tree next : tree[now].childs) {
            dfs(tree, next.number, sum + next.weight);
        }

        if (now == 1)
            return;

        dfs(tree, tree[now].parent, sum + tree[now].weight);
    }
}