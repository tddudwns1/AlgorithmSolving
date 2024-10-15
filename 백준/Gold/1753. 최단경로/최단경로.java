import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        Queue<Edge>[] ways = new Queue[v + 1];
        for (int i = 1; i <= v; i++) {
            ways[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            ways[from].add(new Edge(to, weight));
        }

        System.out.println(dijkstra(v, start, ways));
    }

    private static String dijkstra(int v, int start, Queue<Edge>[] ways) {
        Queue<Edge> pq = new PriorityQueue<>();

        pq.add(new Edge(start, 0));

        int[] weights = new int[v + 1];
        boolean[] visited = new boolean[v + 1];

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visited[now.destination])
                continue;
            visited[now.destination] = true;
            weights[now.destination] = now.weight;

            while(!ways[now.destination].isEmpty()) {
                Edge poll = ways[now.destination].poll();
                if (visited[poll.destination])
                    continue;
                poll.weight += now.weight;
                pq.add(poll);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (visited[i])
                sb.append(weights[i]).append("\n");
            else
                sb.append("INF\n");
        }
        return sb.toString();
    }
}