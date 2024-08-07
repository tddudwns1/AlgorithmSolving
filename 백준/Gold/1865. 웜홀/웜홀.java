import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[m * 2 + w];
            int index = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges[index++] = new Edge(s, e, t);
                edges[index++] = new Edge(e, s, t);
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges[index++] = new Edge(s, e, -t);
            }

            sb.append(bellmanFord(edges, n)).append("\n");
        }

        System.out.println(sb);
    }

    private static String bellmanFord(Edge[] edges, int n) {
        int[] times = new int[n + 1];
        Arrays.fill(times, 5_000_000);
        times[0] = 0;

        for (int cycle = 1; cycle < n; cycle++) {
            boolean cantReturn = true;
            for (Edge edge : edges) {
                int candidate = times[edge.start] + edge.cost;
                if (times[edge.end] <= candidate)
                    continue;

                times[edge.end] = candidate;
                cantReturn = false;
            }

            if (cantReturn)
                return "NO";
        }
        for (Edge edge : edges) {
            if (times[edge.end] <= times[edge.start] + edge.cost)
                continue;

            return "YES";
        }

        return "NO";
    }
}