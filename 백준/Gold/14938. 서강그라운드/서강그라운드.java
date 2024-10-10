import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int target;
        int distance;

        public Node(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역 개수
        int m = Integer.parseInt(st.nextToken()); // 수색범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        st = new StringTokenizer(br.readLine());
        int[] itemCounts = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            itemCounts[i] = Integer.parseInt(st.nextToken());
        }

        List<Node>[] distances = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            distances[i] = new ArrayList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 지역 a
            int b = Integer.parseInt(st.nextToken()); // 지역 b
            int l = Integer.parseInt(st.nextToken()); // 길의 길이

            distances[a].add(new Node(b, l));
            distances[b].add(new Node(a, l));
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, djikstra(i, n, m, distances, itemCounts));
        }

        System.out.println(answer);
    }

    private static int djikstra(int start, int n, int m, List<Node>[] distances, int[] itemCounts) {
        boolean[] visited = new boolean[n + 1];

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int total = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.target])
                continue;
            visited[now.target] = true;

            total += itemCounts[now.target];

            for (Node next : distances[now.target]) {
                int newDistance = now.distance + next.distance;
                if (newDistance > m)
                    continue;

                pq.add(new Node(next.target, newDistance));
            }
        }

        return total;
    }
}