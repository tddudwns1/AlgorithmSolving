import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int position;
        int cost;

        public Node(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "position=" + position +
                    ", cost=" + cost +
                    '}';
        }
    }

    static Map<Integer, List<Node>> map;
    static int n, d;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (e > d)
                continue;

            List<Node> pq = map.getOrDefault(s, new ArrayList<>());
            pq.add(new Node(e, cost));
            map.put(s, pq);
//            map.computeIfAbsent(s, integer -> new ArrayList<>()).add(new Node(e, cost));
        }

        dp = new int[d + 1];
        for (int i = 0; i <= d; i++) dp[i] = i;

        dijkstra();

        System.out.println(dp[d]);
    }

    public static void dijkstra() {
        for (int cur = 0; cur < d; cur++) {
            if (dp[cur + 1] > dp[cur] + 1)
                dp[cur + 1] = dp[cur] + 1;

            if (map.containsKey(cur)) {
                for (Node next : map.get(cur)) {
                    int newCost = dp[cur] + next.cost;
                    if (dp[next.position] <= newCost)
                        continue;
                    dp[next.position] = newCost;
                }
            }
        }
    }
}