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

        char[][] moNoon = new char[n + 2][m + 2];
        Queue<Point> cheezes = new ArrayDeque<>();

        for (int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= m; x++) {
                moNoon[y][x] = st.nextToken().charAt(0);
                if (moNoon[y][x] == '1')
                    cheezes.add(new Point(y, x));
            }
        }

        Queue<Point> candidateOutsideAir = new ArrayDeque<>();
        candidateOutsideAir.add(new Point(1, 1));

        int hours = 0;

        while (true) {
            checkOutsideAir(moNoon, candidateOutsideAir);

            Queue<Point> candidateMeltCheeze = new ArrayDeque<>();
            if (!checkMeltCheeze(moNoon, cheezes, candidateMeltCheeze, candidateOutsideAir))
                break;

            for (Point now : candidateMeltCheeze)
                moNoon[now.y][now.x] = 'a';

            hours++;
        }

        System.out.println(hours);
    }

    private static boolean checkMeltCheeze(char[][] moNoon, Queue<Point> cheezes, Queue<Point> candidateMeltCheeze, Queue<Point> candidateOutsideAir) {
        Iterator<Point> iterator = cheezes.iterator();
        boolean isChange = false;

        while (iterator.hasNext()) {
            Point now = iterator.next();
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (moNoon[dy][dx] != 'a')
                    continue;

                count++;
            }

            if (count < 2)
                continue;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (moNoon[dy][dx] != '0')
                    continue;

                candidateOutsideAir.add(new Point(dy, dx));
            }

            isChange = true;

            candidateMeltCheeze.add(new Point(now.y, now.x));
            iterator.remove();
        }

        return isChange;
    }

    private static void checkOutsideAir(char[][] moNoon, Queue<Point> candidateOutsideAir) {
        for (Point now : candidateOutsideAir)
            moNoon[now.y][now.x] = 'a';

        while (!candidateOutsideAir.isEmpty()) {
            Point now = candidateOutsideAir.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (moNoon[dy][dx] == '\0')
                    continue;

                if (moNoon[dy][dx] != '0')
                    continue;

                moNoon[dy][dx] = 'a';
                candidateOutsideAir.add(new Point(dy, dx));
            }
        }
    }
}