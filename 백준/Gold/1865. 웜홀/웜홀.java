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

            int[][] infos = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++)
                Arrays.fill(infos[i], Integer.MAX_VALUE);

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                infos[e][s] = infos[s][e] = Math.min(infos[s][e], t);
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                infos[s][e] = Math.min(infos[s][e], -t);
            }

            Queue<Edge> edges = new ArrayDeque<>();
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (infos[i][j] != Integer.MAX_VALUE)
                        edges.add(new Edge(i, j, infos[i][j]));

            sb.append(bellmanFord(edges, n)).append("\n");
        }

        System.out.println(sb);
    }

    private static String bellmanFord(Queue<Edge> edges, int n) {
        int[] times = new int[n + 1];
        Arrays.fill(times, 500 * 10_000);
        times[0] = 0;

        for (int cycle = 1; cycle < n; cycle++) {
            boolean cantReturn = true;
            for (Edge edge : edges) {
                if (times[edge.end] <= times[edge.start] + edge.cost)
                    continue;

                times[edge.end] = times[edge.start] + edge.cost;
                cantReturn = false;
            }
            
            if (cantReturn)
                return "NO";
        }
        for (Edge edge : edges) {
            if (times[edge.start] == Integer.MAX_VALUE)
                continue;

            if (times[edge.end] <= times[edge.start] + edge.cost)
                continue;

            return "YES";
        }

        return "NO";
    }
}