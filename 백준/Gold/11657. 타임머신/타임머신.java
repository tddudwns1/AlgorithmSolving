import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
    int s;
    int e;
    int t;

    public Edge(int s, int e, int t) {
        this.s = s;
        this.e = e;
        this.t = t;
    }
}

/**
 * 벨만 포드 기본 문제
 */
public class Main {
    static final Long MAX = 30_0000_0000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(s, e, t);
        }

        long[] times = new long[n + 1];
        Arrays.fill(times, MAX);

        if (bellmanFord(edges, times, n)){
            StringBuilder sb = new StringBuilder();

            for (int i = 2; i <= n; i++) {
                if (times[i] == MAX)
                    sb.append(-1);
                else
                    sb.append(times[i]);
                sb.append("\n");
            }

            System.out.println(sb);
        }
        else
            System.out.println(-1);
    }

    private static boolean bellmanFord(Edge[] edges, long[] times, int n) {
        times[1] = 0;

        for (int count = 0; count < n; count++) {
            for (Edge now : edges) {
                if (times[now.s] == MAX)
                    continue;

                long newTime = times[now.s] + now.t;
                if (times[now.e] <= newTime)
                    continue;

                times[now.e] = newTime;
            }
        }

        for (Edge now : edges) {
            if (times[now.s] == MAX)
                continue;

            long newTime = times[now.s] + now.t;
            if (times[now.e] <= newTime)
                continue;

            return false;
        }

        return true;
    }
}