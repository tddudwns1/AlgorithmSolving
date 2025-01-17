import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Info implements Comparable<Info> {
        int start;
        int end;
        int length;

        public Info(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Info o) {
            return length - o.length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Info>[] connects = new Queue[n + 1];
        for (int i = 1; i <= n; i++) {
            connects[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            connects[a].add(new Info(a, b, c));
            connects[b].add(new Info(b, a, c));
        }

        System.out.println(process(n, connects));
    }

    private static String process(int n, Queue<Info>[] connects) {
        StringBuilder sb = new StringBuilder();
        boolean[] checked = new boolean[n + 1];
        Queue<Info> pq = new PriorityQueue<>();
        Queue<Info> q = new ArrayDeque<>();
        pq.add(new Info(0, 1, 0));

        while (!pq.isEmpty()) {
            Info now = pq.poll();

            if (checked[now.end]) {
                continue;
            }
            checked[now.end] = true;

            q.add(now);

            for (Info next : connects[now.end]) {
                pq.add(new Info(next.start, next.end, now.length+next.length));
            }
        }

        q.poll();
        sb.append(q.size()).append("\n");
        while (!q.isEmpty()) {
            Info now = q.poll();
            sb.append(now.start).append(" ").append(now.end).append("\n");
        }

        return sb.toString();
    }
}