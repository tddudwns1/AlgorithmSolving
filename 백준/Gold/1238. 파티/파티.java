import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Info implements Comparable<Info> {
        int destination;
        int distance;

        public Info(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o) {
            return distance - o.distance;
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
            dijkstra(i, x, dist, n, infos);

        int answer = 0;
        for (int i = 1; i <= n; i++)
            answer = Math.max(answer, dist[i][x] + dist[x][i]);

        System.out.println(answer);
    }

    public static void dijkstra(int start, int destination, int[][] dist, int n, List<Info>[] infos) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        dist[start][start] = 0;
        pq.add(new Info(start, 0));

        while (n > 0) {
            Info now = pq.poll();
            int index = now.destination;

            if (visited[index])
                continue;
            visited[index] = true;

            for (Info next : infos[index])
                if (dist[start][next.destination] > dist[start][index] + next.distance) {
                    dist[start][next.destination] = dist[start][index] + next.distance;
                    pq.add(new Info(next.destination, dist[start][next.destination]));
                }

            n--;

            if (index == destination)
                if (start != destination)
                    return;
        }
    }
}