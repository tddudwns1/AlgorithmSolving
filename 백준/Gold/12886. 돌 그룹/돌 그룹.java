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

            if (a != b)
                if (a < b)
                    addQ(a, b, now.c, q, visited);
                else
                    addQ(b, a, now.c, q, visited);


            if (a != c)
                if (a < c)
                    addQ(a, c, now.b, q, visited);
                else
                    addQ(c, a, now.b, q, visited);


            if (b != c)
                if (b < c)
                    addQ(b, c, now.a, q, visited);
                else
                    addQ(c, b, now.a, q, visited);
        }

        return 0;
    }

    private static void addQ(int small, int big, int other, Queue<Stone> q, boolean[][] visited) {
        if (small > 350)
            return;

        int doubleSmall = small << 1;
        int bigMinusSmall = big - small;

        if (visited[doubleSmall][bigMinusSmall])
            return;

        visited[doubleSmall][bigMinusSmall] = true;

        q.add(new Stone(doubleSmall, bigMinusSmall, other));
    }

}