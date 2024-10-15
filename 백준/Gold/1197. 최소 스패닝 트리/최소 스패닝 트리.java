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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Node(a, b, c));
        }

        System.out.println(process(v, pq));
    }

    private static int process(int v, Queue<Node> pq) {
        int[] parents = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }
        int answer = 0;
        int count = v;

        while(count > 1) {
            Node now = pq.poll();

            if (!union(now.from, now.to, parents))
                continue;

            answer += now.cost;
            count--;
        }
        return answer;
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