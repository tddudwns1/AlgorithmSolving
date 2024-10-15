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
        int cost;

        public Node(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0)
                break;

            Queue<Node>[] infos = new Queue[m];
            for (int i = 0; i < m; i++) {
                infos[i] = new ArrayDeque<>();
            }

            int total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                infos[x].add(new Node(y, z));
                infos[y].add(new Node(x, z));
                total += z;
            }

            int cost = prim(m, infos);

            sb.append(total - cost).append("\n");
        }

        System.out.println(sb);
    }

    private static int prim(int m, Queue<Node>[] infos) {
        boolean[] visited = new boolean[m];
        int cost = 0;
        int count = m;

        Queue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(0, 0));

        while (count > 0) {
            Node now = pq.poll();

            if(visited[now.destination])
                continue;
            visited[now.destination] = true;
            cost += now.cost;
            count--;

            while(!infos[now.destination].isEmpty()) {
                Node next = infos[now.destination].poll();
                if (visited[next.destination])
                    continue;
                pq.add(next);
            }
        }

        return cost;
    }
}