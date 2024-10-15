import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int destination;
        int weight;

        public Node(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        Queue<Node>[][] info = new ArrayDeque[2][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                info[j][i] = new ArrayDeque<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 2; j++) {
                info[0][a].add(new Node(b, weight));
                info[1][b].add(new Node(a, weight));
            }
        }

        System.out.println(process(n, x, info));
    }

    private static int process(int n, int x, Queue<Node>[][] info) {
        int[][] weights = new int[2][n + 1];

        for (int i = 0; i < 2; i++) {
            dijkstra(n, x, weights[i], info[i]);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j < 2; j++) {
                sum += weights[j][i];
            }
            max = Math.max(max, sum);
        }

        return max;
    }

    private static void dijkstra(int n, int x, int[] weights, Queue<Node>[] queues) {
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        pq.add(new Node(x, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.destination])
                continue;
            visited[now.destination] = true;
            weights[now.destination] = now.weight;

            while (!queues[now.destination].isEmpty()) {
                Node next = queues[now.destination].poll();

                if (visited[next.destination])
                    continue;
                next.weight += now.weight;
                pq.add(next);
            }
        }
    }
}