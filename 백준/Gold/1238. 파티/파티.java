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

        List<Info>[] goInfos = new List[n + 1];
        for (int i = 1; i <= n; i++)
            goInfos[i] = new ArrayList<>();

        List<Info>[] backInfos = new List[n + 1];
        for (int i = 1; i <= n; i++)
            backInfos[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            goInfos[s].add(new Info(e, d));
            backInfos[e].add(new Info(s, d));
        }

        int[] goDist = new int[n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(goDist, Integer.MAX_VALUE);
        dijkstra(x, goDist, n, goInfos);

        int[] backDist = new int[n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(backDist, Integer.MAX_VALUE);
        dijkstra(x, backDist, n, backInfos);

        int answer = 0;
        for (int i = 1; i <= n; i++)
            answer = Math.max(answer, goDist[i] + backDist[i]);

        System.out.println(answer);
    }

    public static void dijkstra(int destination, int[] dist, int n, List<Info>[] infos) {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        dist[destination] = 0;
        pq.add(new Info(destination, 0));

        while (n > 0) {
            Info now = pq.poll();
            int index = now.destination;

            if (dist[index] < now.distance)
                continue;

            for (Info next : infos[index])
                if (dist[next.destination] > dist[index] + next.distance) {
                    dist[next.destination] = dist[index] + next.distance;
                    pq.add(new Info(next.destination, dist[next.destination]));
                }

            n--;
        }
    }
}