import java.io.*;
import java.util.*;

public class Main {
    static class Bus implements Comparable<Bus> {
        int station;
        int cost;

        public Bus(int destination, int cost) {
            this.station = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] cities = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(cities[i], Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            cities[from][to] = Integer.min(cities[from][to], cost);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end, n, cities));
    }

    private static int dijkstra(int start, int end, int n, int[][] cities) {
        Queue<Bus> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[start] = 0;
        pq.add(new Bus(start, 0));

        while (!pq.isEmpty()) {
            int now = pq.poll().station;

            if(now == end)
                break;
            if(visited[now])
                continue;
            visited[now] = true;

            for (int i = 1; i <= n; i++) {
                if(cities[now][i] == Integer.MAX_VALUE)
                    continue;

                if(visited[i])
                    continue;

                int newCost = costs[now] + cities[now][i];
                if(costs[i] <= newCost)
                    continue;

                costs[i] = newCost;
                pq.add(new Bus(i, costs[i]));
            }
        }

        return costs[end];
    }
}