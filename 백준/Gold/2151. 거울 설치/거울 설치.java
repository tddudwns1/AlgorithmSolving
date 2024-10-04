import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int y;
        int x;
        boolean isHorizon;

        public Point(int y, int x, boolean isHorizon) {
            this.y = y;
            this.x = x;
            this.isHorizon = isHorizon;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Point start = null;

        char[][] house = new char[n + 2][n + 2];
        for (int y = 1; y <= n; y++) {
            char[] line = br.readLine().toCharArray();

            for (int x = 1; x <= n; x++) {
                house[y][x] = line[x - 1];

                if (house[y][x] == '#')
                    start = new Point(y, x, true);
            }
        }

        house[start.y][start.x] = '.';

        System.out.println(process(start, house));
    }

    private static int process(Point start, char[][] house) {
        int[][] moveH = {{0, 1}, {0, -1}};
        int[][] moveV = {{-1, 0}, {1, 0}};

        int answer = 0;
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        q.add(new Point(start.y, start.x, false));

        portal:
        while (true) {
            int size = q.size();

            while (size-- > 0) {
                Point now = q.poll();

                if (now.isHorizon) {
                    if (isFindDoor(now, q, moveH, house))
                        break portal;
                } else {
                    if (isFindDoor(now, q, moveV, house))
                        break portal;
                }
            }

            answer++;
        }

        return answer;
    }

    static boolean isFindDoor(Point now, Queue<Point> q, int[][] move, char[][] house) {

        for (int i = 0; i < 2; i++) {
            int dy = now.y;
            int dx = now.x;

            while (true) {
                dy += move[i][0];
                dx += move[i][1];

                if (house[dy][dx] == '\0')
                    break;

                if (house[dy][dx] == '!')
                    q.add(new Point(dy, dx, !now.isHorizon));

                if (house[dy][dx] == '*')
                    break;

                if (house[dy][dx] == '#')
                    return true;
            }
        }

        return false;
    }
}