import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static class Info {
        int destination;
        int distance;

        public Info(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Info>[] infos = new List[n + 1];
        for (int i = 1; i <= n; i++)
            infos[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            infos[s].add(new Info(e, d));
        }

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++)
            dijkstra(i, x, dist, new boolean[n + 1], n, infos);

        int answer = 0;
        for (int i = 1; i <= n; i++)
            answer = Math.max(answer, dist[i][x] + dist[x][i]);

        System.out.println(answer);
    }

    public static void dijkstra(int start, int destination, int[][] dist, boolean[] visited, int n, List<Info>[] infos) {
        dist[start][start] = 0;

        for (int v = 0; v < n; v++) {
            int temp = Integer.MAX_VALUE;
            int index = 0;

            for (int i = 1; i <= n; i++) {
                if (visited[i])
                    continue;

                if (dist[start][i] >= temp)
                    continue;

                temp = dist[start][index = i];
            }

            visited[index] = true;

            for (Info now : infos[index])
                if (dist[start][now.destination] > dist[start][index] + now.distance)
                    dist[start][now.destination] = dist[start][index] + now.distance;

            if (index == destination)
                if (start != destination)
                    return;
        }
    }
}