import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] moNoon = new int[n][m];
        Queue<Point> cheezes = new ArrayDeque<>();

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                moNoon[y][x] = Integer.parseInt(st.nextToken());
                if (moNoon[y][x] == 1)
                    cheezes.add(new Point(y, x));
            }
        }

        Queue<Point> candidateOutsideAir = new ArrayDeque<>();
        candidateOutsideAir.add(new Point(0, 0));

        int hours = 0;

        while (!cheezes.isEmpty()) {
            checkOutsideAir(moNoon, candidateOutsideAir, n, m);

            Queue<Point> candidateMeltCheeze = new ArrayDeque<>();
            checkMeltCheeze(moNoon, cheezes, candidateMeltCheeze, candidateOutsideAir);
            
            for (Point now : candidateMeltCheeze)
                moNoon[now.y][now.x] = 2;

            hours++;
        }

        System.out.println(hours);
    }

    private static void checkMeltCheeze(int[][] moNoon, Queue<Point> cheezes, Queue<Point> candidateMeltCheeze, Queue<Point> candidateOutsideAir) {
        Iterator<Point> iterator = cheezes.iterator();

        while (iterator.hasNext()) {
            Point now = iterator.next();
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (moNoon[dy][dx] != 2)
                    continue;

                count++;
            }

            if (count < 2)
                continue;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (moNoon[dy][dx] != 0)
                    continue;

                moNoon[dy][dx] = -1;
                candidateOutsideAir.add(new Point(dy, dx));
            }

            candidateMeltCheeze.add(new Point(now.y, now.x));
            iterator.remove();
        }
    }

    private static void checkOutsideAir(int[][] moNoon, Queue<Point> candidateOutsideAir, int n, int m) {
        for (Point now : candidateOutsideAir)
            moNoon[now.y][now.x] = 2;

        while (!candidateOutsideAir.isEmpty()) {
            Point now = candidateOutsideAir.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                if (dy < 0 || dy >= n)
                    continue;

                int dx = now.x + move[i][1];
                if (dx < 0 || dx >= m)
                    continue;

                if (moNoon[dy][dx] > 0)
                    continue;

                moNoon[dy][dx] = 2;
                candidateOutsideAir.add(new Point(dy, dx));
            }
        }
    }
}