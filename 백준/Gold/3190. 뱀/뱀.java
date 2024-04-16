import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        public int y;
        public int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        char[][] map = new char[n + 2][n + 2];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                map[i][j] = 'x';

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 'a';
        }

        int l = Integer.parseInt(br.readLine());
        int count = 0;
        int direction = 1;
        Point now = new Point(1, 1);
        Queue<Point> tail = new ArrayDeque<>();
        map[1][1] = 'o';
        tail.add(new Point(1, 1));

        portal:
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            while (true) {
                if (move(map, now, direction, tail))
                    break portal;

                if (++count == x)
                    break;
            }

            if (d.equals("L"))
                direction = (direction + 3) % 4;
            else
                direction = (direction + 1) % 4;
        }


        while (true) {
            ++count;

            if (move(map, now, direction, tail))
                break;
        }

        System.out.println(count);
    }

    private static boolean move(char[][] map, Point now, int direction, Queue<Point> tail) {
        int dy = now.y + move[direction][0];
        int dx = now.x + move[direction][1];

        if (map[dy][dx] == '\0')
            return true;

        if (map[dy][dx] == 'o')
            return true;

        if (map[dy][dx] != 'a') {
            Point remove = tail.poll();
            map[remove.y][remove.x] = 'x';
        }

        now.y = dy;
        now.x = dx;
        map[dy][dx] = 'o';
        tail.add(new Point(dy, dx));

        return false;
    }
}