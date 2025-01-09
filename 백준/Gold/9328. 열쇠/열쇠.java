import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int keys;

    static int stealCount;

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
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h + 2][w + 2];
            for (int y = 1; y <= h; y++) {
                int x = 1;
                char[] line = br.readLine().toCharArray();
                for (char c : line) {
                    map[y][x++] = c;
                }
            }

            char[] hasKeys = br.readLine().toCharArray();

            process(h, w, map, hasKeys);

            sb.append(stealCount).append("\n");
        }

        System.out.println(sb);
    }

    private static void process(int h, int w, char[][] map, char[] hasKeys) {
        stealCount = 0;

        keys = 0;
        if (hasKeys[0] != '0') {
            for (char c : hasKeys) {
                addKey(c);
            }
        }

        boolean[][] visited = new boolean[h + 2][w + 2];
        initVisited(visited, h + 1, w + 1);

        Queue<Point> wait = new ArrayDeque<>();
        Queue<Point> free = new ArrayDeque<>();

        scanOutLine(h, w, map, visited, wait, free);

        scanFull(map, visited, wait, free);
    }

    private static void scanFull(char[][] map, boolean[][] visited, Queue<Point> wait, Queue<Point> free) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (true) {
            int size = wait.size();

            while (size-- > 0) {
                Point now = wait.poll();

                if (canOpenDoor(map[now.y][now.x])) {
                    visited[now.y][now.x] = true;
                    free.add(now);
                } else {
                    wait.add(now);
                }
            }

            if (free.isEmpty()) {
                break;
            }

            while (!free.isEmpty()) {
                Point now = free.poll();

                for (int i = 0; i < 4; i++) {
                    int dy = now.y + move[i][0];
                    int dx = now.x + move[i][1];

                    if (visited[dy][dx]) {
                        continue;
                    }

                    checkPoint(dy, dx, map, visited, wait, free);
                }
            }
        }
    }

    private static void scanOutLine(int h, int w, char[][] map, boolean[][] visited, Queue<Point> wait, Queue<Point> free) {
        for (int x = 1; x <= w; x++) {
            checkPoint(1, x, map, visited, wait, free);
        }
        for (int x = 1; x <= w; x++) {
            checkPoint(h, x, map, visited, wait, free);
        }

        for (int y = 2; y < h; y++) {
            checkPoint(y, 1, map, visited, wait, free);
        }
        for (int y = 2; y < h; y++) {
            checkPoint(y, w, map, visited, wait, free);
        }
    }

    private static void checkPoint(int y, int x, char[][] map, boolean[][] visited, Queue<Point> wait, Queue<Point> free) {
        char c = map[y][x];
        Point now = new Point(y, x);

        visited[y][x] = true;

        if (Character.isUpperCase(c) && !canOpenDoor(c)) {
            wait.add(now);
            return;
        }

        if (c == '*') {
            return;
        }

        free.add(now);

        if (c == '$') {
            stealCount++;
            return;
        }

        if (Character.isLowerCase(c)) {
            addKey(c);
        }
    }

    private static boolean canOpenDoor(char c) {
        return (keys & 1 << (c - 'A')) > 0;
    }

    private static void addKey(char c) {
        keys = keys | (1 << (c - 'a'));
    }

    private static void initVisited(boolean[][] visited, int h, int w) {
        for (int x = 0; x <= w; x++) {
            visited[0][x] = true;
        }
        for (int x = 0; x <= w; x++) {
            visited[h][x] = true;
        }

        for (int y = 1; y < h; y++) {
            visited[y][0] = true;
        }
        for (int y = 1; y < h; y++) {
            visited[y][w] = true;
        }
    }
}