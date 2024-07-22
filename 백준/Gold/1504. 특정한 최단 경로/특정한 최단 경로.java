import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        int end;
        int distance;

        public Info(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o){
            return distance - o.distance;
        }
    }

    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Queue<Info>[] distances = new ArrayDeque[n + 1];
        for(int i = 1; i <= n; i++)
            distances[i] = new ArrayDeque<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distances[a].add(new Info(b, c));
            distances[b].add(new Info(a, c));
        }

        int[] order = new int[2];
        st = new StringTokenizer(br.readLine());
        order[0] = Integer.parseInt(st.nextToken());
        order[1] = Integer.parseInt(st.nextToken());

        int[] total = new int[n + 1];

        System.out.println(process(n, distances, order, total));
    }

    private static int process(int n, Queue<Info>[] distances, int[] order, int[] total) {
        Info stopover = new Info(1, 0);

        for (int i = 0; i <= 1; i++) {
            stopover = dijkstra(stopover, n, distances, order[i], total);
            if (stopover.end == -1)
                return -1;
        }

        Info candidate1 = dijkstra(stopover, n, distances, n, total);
        if (candidate1.end == -1)
            return -1;

        stopover = new Info(1, 0);

        for (int i = 1; i >= 0; i--) {
            stopover = dijkstra(stopover, n, distances, order[i], total);
            if (stopover.end == -1)
                return -1;
        }

        Info candidate2 = dijkstra(stopover, n, distances, n, total);
        if (candidate2.end == -1)
            return -1;

        return Math.min(candidate1.distance, candidate2.distance);
    }

    private static Info dijkstra(Info start, int n, Queue<Info>[] distances, int target, int[] total) {
        Queue<Info> pq = new PriorityQueue<>();
        Arrays.fill(total, Integer.MAX_VALUE);
        boolean[] checked = new boolean[n + 1];

        pq.add(start);
        total[start.end] = start.distance;

        while(!pq.isEmpty()) {
            Info now = pq.poll();

            if (checked[now.end])
                continue;
            checked[now.end] = true;

            if (target == now.end)
                return new Info(now.end, now.distance);

            for(Info next : distances[now.end])
                if (total[next.end] > total[now.end] + next.distance)
                    pq.add(new Info(next.end, total[next.end] = total[now.end] + next.distance));
        }

        return new Info(-1, -1);
    }
}