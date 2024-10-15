import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0)
                break;

            Queue<Node> pq = new PriorityQueue<>();
            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                pq.add(new Node(x, y, z));
                total += z;
            }

            int cost = prim(m, pq);

            sb.append(total - cost).append("\n");
        }

        System.out.println(sb);
    }

    private static int prim(int m, Queue<Node> pq) {
        int[] parents = new int[m];
        for (int i = 0; i < m; i++) {
            parents[i] = i;
        }
        int cost = 0;
        int count = m;

        while (count > 1) {
            Node now = pq.poll();

            if (!union(now.from, now.to, parents))
                continue;

            cost += now.cost;
            count--;
        }

        return cost;
    }

    private static int find(int n, int[] parents) {
        if (parents[n] == n)
            return n;

        return parents[n] = find(parents[n], parents);
    }

    private static boolean union(int a, int b, int[] parents) {
        int pA = find(a, parents);
        int pB = find(b, parents);

        if (pA == pB)
            return false;
        parents[pB] = pA;
        return true;
    }
}