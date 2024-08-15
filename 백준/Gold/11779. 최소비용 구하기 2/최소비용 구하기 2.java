import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info>{
        int city;
        long weight;

        public Info(int city, long weight) {
            this.city = city;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Info>[] weights = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            weights[i] = new ArrayList();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            weights[start].add(new Info(end, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(weights, start, end, n));
    }

    private static String dijkstra(List<Info>[] weights, int start, int end, int n) {
        Queue<Info> pq = new PriorityQueue<>();
        Info[] confirmed = new Info[n + 1];

        pq.add(new Info(start, 0));
        confirmed[start] = new Info(-1, 0);

        Stack<Integer> stack = new Stack<>();

        while(true) {
            Info now = pq.poll();

            if (now.city == end)
                break;

            for (Info next : weights[now.city]) {
                long newWeight = now.weight + next.weight;
                if (confirmed[next.city] != null && confirmed[next.city].weight <= newWeight)
                    continue;

                confirmed[next.city] = new Info(now.city, newWeight);

                pq.add(new Info(next.city, newWeight));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(confirmed[end].weight).append("\n");

        while (end != -1) {
            stack.add(end);

            end = confirmed[end].city;
        }

        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");

        return sb.toString();
    }
}