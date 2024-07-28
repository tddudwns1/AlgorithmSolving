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
        if((stone.a + stone.b + stone.c) % 3 != 0)
            return 0;

        boolean[][] visited = new boolean[1500][1500];

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
                int newA = a + a;
                int newB = b - a;

                if (visited[newA][newB])
                    continue;
                visited[newA][newB] = true;

                q.add(new Stone(newA, newB, now.c));
            } else if (a > b) {
                int newA = a - b;
                int newB = b + b;

                if (visited[newA][newB])
                    continue;
                visited[newA][newB] = true;

                q.add(new Stone(newA, newB, now.c));
            }

            if (a < c) {
                int newA = a + a;
                int newC = c - a;

                if (visited[newA][newC])
                    continue;
                visited[newA][now.c] = true;

                q.add(new Stone(newA, now.b, newC));
            } else if (a > c) {
                int newA = a - c;
                int newC = c + c;

                if (visited[newA][newC])
                    continue;
                visited[newA][now.c] = true;

                q.add(new Stone(newA, now.b, newC));
            }

            if (b < c) {
                int newB = b + b;
                int newC = c - b;

                if (visited[newB][newC])
                    continue;
                visited[newB][now.c] = true;

                q.add(new Stone(now.a, newB, newC));
            } else if (b > c) {
                int newB = b - c;
                int newC = c + c;

                if (visited[newB][newC])
                    continue;
                visited[newB][now.c] = true;

                q.add(new Stone(now.a, newB, newC));
            }
        }

        return 0;
    }
}