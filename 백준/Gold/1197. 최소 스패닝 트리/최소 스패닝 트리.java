import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Deque<Edge>[] edges = new Deque[v + 1];
        for (int i = 1; i <= v; i++)
            edges[i] = new ArrayDeque<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        System.out.println(prim(edges, v));
    }

    private static int prim(Deque<Edge>[] edges, int v) {
        Queue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1];

        pq.add(new Edge(1, 0));

        int answer = 0;
        int count = v;
        while (count > 0) {
            Edge now = pq.poll();

            if (visited[now.node])
                continue;
            visited[now.node] = true;
            answer += now.weight;
            count--;

            for (Edge next : edges[now.node])
                pq.add(next);
        }

        return answer;
    }
}