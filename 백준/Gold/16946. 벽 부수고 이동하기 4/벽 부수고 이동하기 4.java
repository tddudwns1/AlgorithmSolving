import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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

        char[][] map = new char[n + 2][m + 2];
        for (int y = 1; y <= n; y++) {
            char[] line = br.readLine().toCharArray();
            for (int x = 1; x <= m; x++) {
                map[y][x] = line[x - 1];
            }
        }

        System.out.println(process(n, m, map));
    }

    private static String process(int n, int m, char[][] map) {
        Queue<Point> walls = new ArrayDeque<>();

        boolean[][] visited = new boolean[n + 1][m + 1];
        int[][] checkNum = new int[n + 2][m + 2];
        int[][] checkAround = new int[n + 2][m + 2];
        init(n, m, map, checkNum, checkAround, visited, walls);

        int[][] answer = new int[n + 2][m + 2];
        return check(n, m, answer, checkNum, checkAround, walls);
    }

    private static String check(int n, int m, int[][] answer, int[][] checkNum, int[][] checkAround, Queue<Point> walls) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!walls.isEmpty()) {
            Point now = walls.poll();
            Set<Integer> num = new HashSet<>();
            int count = 1;

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (!num.contains(checkNum[dy][dx])) {
                    count += checkAround[dy][dx];
                    num.add(checkNum[dy][dx]);
                }
            }

            answer[now.y][now.x] = count % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                sb.append(answer[y][x]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static void init(int n, int m, char[][] map, int[][] checkNum, int[][] checkAround, boolean[][] visited, Queue<Point> walls) {
        int areaNum = 1;
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (visited[y][x]) {
                    continue;
                }
                visited[y][x] = true;

                if (map[y][x] == '1') {
                    walls.add(new Point(y, x));
                } else if (checkAround[y][x] == 0) {
                    checkAround(map, checkNum, checkAround, visited, y, x, areaNum++);
                }
            }
        }
    }

    private static void checkAround(char[][] map, int[][] checkNum, int[][] checkAround, boolean[][] visited, int y, int x, int areaNum) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<Point> q = new ArrayDeque<>();
        Queue<Point> check = new ArrayDeque<>();
        q.add(new Point(y, x));
        check.add(new Point(y, x));

        int count = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int dy = now.y + move[i][0];
                int dx = now.x + move[i][1];

                if (map[dy][dx] == '\0') {
                    continue;
                }

                if (map[dy][dx] == '1') {
                    continue;
                }

                if (visited[dy][dx]) {
                    continue;
                }
                visited[dy][dx] = true;

                count++;
                q.add(new Point(dy, dx));
                check.add(new Point(dy, dx));
            }
        }

        while (!check.isEmpty()) {
            Point now = check.poll();
            checkNum[now.y][now.x] = areaNum;
            checkAround[now.y][now.x] = count;
        }
    }
}