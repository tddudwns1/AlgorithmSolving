import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from;
        double distance;

        public Edge(int from, double distance) {
            this.from = from;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.distance > o.distance)
                return 1;
            if (this.distance < o.distance)
                return -1;
            return 0;
        }
    }

    static class Node {
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] stars = new Node[n];
        Queue<Edge>[] distances = new PriorityQueue[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            stars[i] = new Node(a, b);

            distances[i] = new PriorityQueue<>();

            for (int j = 0; j < i; j++) {
                double dx = Math.pow(stars[j].x - stars[i].x, 2);
                double dy = Math.pow(stars[j].y - stars[i].y, 2);
                double distance = Math.sqrt(dx + dy);
                distance = Math.round(distance * 100.0) / 100.0;

                distances[i].add(new Edge(j, distance));
                distances[j].add(new Edge(i, distance));
            }
        }

        System.out.println(prim(distances, n));
    }

    public static double prim(Queue<Edge>[] distances, int n) {
        boolean[] visited = new boolean[n];
        double cost = 0;

        int count = 1;
        visited[0] = true;

        while (count != n) {
            Edge now = distances[0].poll();

            if(visited[now.from])
                continue;

            cost += now.distance;
            visited[now.from] = true;
            ++count;
            while(!distances[now.from].isEmpty())
                distances[0].add(distances[now.from].poll());
        }
        return cost;
    }
}