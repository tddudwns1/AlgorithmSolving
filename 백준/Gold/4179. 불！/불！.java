import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int count = 0;
    static String answer = "IMPOSSIBLE";

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

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] maze = new char[r + 2][c + 2];
        Queue<Point> fires = new ArrayDeque<>();
        Queue<Point> jihuns = new ArrayDeque<>();
        for (int y = 1; y <= r; y++) {
            char[] line = br.readLine().toCharArray();
            for (int x = 1; x <= c; x++) {
                maze[y][x] = line[x - 1];

                if (maze[y][x] == 'F') {
                    fires.add(new Point(y, x));
                } else if (maze[y][x] == 'J') {
                    jihuns.add(new Point(y, x));
                }
            }
        }

        while (true) {
            count++;
            spread(maze, fires);
            if (!run(maze, jihuns))
                break;
        }

        System.out.println(answer);
    }

    private static boolean run(char[][] maze, Queue<Point> jihuns) {
        int size = jihuns.size();
        while (size-- > 0) {
            Point now = jihuns.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (maze[dy][dx] == '\0') {
                    answer = Integer.toString(count);
                    return false;
                }

                if (maze[dy][dx] != '.')
                    continue;

                jihuns.add(new Point(dy, dx));
                maze[dy][dx] = 'J';
            }
        }

        return !jihuns.isEmpty();
    }

    private static void spread(char[][] maze, Queue<Point> fires) {
        getSpreadSpace(maze, fires);
    }

    private static void getSpreadSpace(char[][] maze, Queue<Point> fires) {
        int size = fires.size();
        while (size-- > 0) {
            Point now = fires.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (maze[dy][dx] == '\0')
                    continue;

                if (maze[dy][dx] == '#' || maze[dy][dx] == 'F')
                    continue;

                fires.add(new Point(dy, dx));
                maze[dy][dx] = 'F';
            }
        }
    }
}