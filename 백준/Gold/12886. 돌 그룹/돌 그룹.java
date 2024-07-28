import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Stone {
        int a;
        int b;
        int c;

        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(process(new Stone(a, b, c)));
    }

    private static int process(Stone stone) {
        if ((stone.a + stone.b + stone.c) % 3 != 0)
            return 0;

        boolean[][] visited = new boolean[1403][1403];

        Queue<Stone> q = new ArrayDeque<>();
        q.add(stone);

        while (!q.isEmpty()) {
            Stone now = q.poll();

            int a = now.a;
            int b = now.b;
            int c = now.c;

            if (a == b && a == c)
                return 1;

            if (a < b) {
                int small = a << 1;
                if (small < 700) {
                    int big = b - a;
                    addQ(small, big, now.c, q, visited);
                }
            } else if (a > b) {
                int small = b << 1;
                if (small < 700) {
                    int big = a - b;
                    addQ(small, big, now.c, q, visited);
                }
            }

            if (a < c) {
                int small = a << 1;
                if (small < 700) {
                    int big = c - a;
                    addQ(small, big, now.b, q, visited);
                }
            } else if (a > c) {
                int small = c << 1;
                if (small < 700) {
                    int big = a - c;
                    addQ(small, big, now.b, q, visited);
                }
            }

            if (b < c) {
                int small = b << 1;
                if (small < 700) {
                    int big = c - b;
                    addQ(small, big, now.a, q, visited);
                }
            } else if (b > c) {
                int small = c << 1;
                if (small < 700) {
                    int big = b - c;
                    addQ(small, big, now.a, q, visited);
                }
            }
        }

        return 0;
    }

    private static void addQ(int small, int big, int other, Queue<Stone> q, boolean[][] visited) {
        if (visited[small][big])
            return;

        visited[small][big] = true;

        q.add(new Stone(small, big, other));
    }

}