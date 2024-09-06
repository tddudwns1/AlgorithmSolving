import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<Point> airPurifier = new ArrayList<>();
        int totalOfDust = 0;

        int[][] home = new int[r + 1][c + 1];
        for (int y = 1; y <= r; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= c; x++) {
                home[y][x] = Integer.parseInt(st.nextToken());

                if (home[y][x] == -1) {
                    airPurifier.add(new Point(y, x));
                } else {
                    totalOfDust += home[y][x];
                }
            }
        }

        System.out.println(process(r, c, t, totalOfDust, airPurifier, home));
    }

    private static int process(int r, int c, int t, int totalOfDust, List<Point> airPurifier, int[][] home) {
        while (t-- > 0) {
            spread(r, c, home);
            totalOfDust -= flow(r, c, airPurifier, home);
        }

        return totalOfDust;
    }

    private static void spread(int r, int c, int[][] home) {
        int[][] memorize = new int[r + 1][c + 1];

        for (int y = 1; y <= r; y++) {
            for (int x = 1; x <= c; x++) {
                if (home[y][x] > 0) {
                    spreadAround(r, c, y, x, home, memorize);
                }
            }
        }

        for (int y = 1; y <= r; y++) {
            for (int x = 1; x <= c; x++) {
                home[y][x] += memorize[y][x];
            }
        }
    }

    private static void spreadAround(int r, int c, int y, int x, int[][] home, int[][] memorize) {
        int[][] move = {{0, 1}, {-1, 0}, {0, -1,}, {1, 0}};
        int peaceOfDust = home[y][x] / 5;

        for (int i = 0; i < 4; i++) {
            int dy = y + move[i][0];
            if (dy < 1 || dy > r) {
                continue;
            }

            int dx = x + move[i][1];
            if (dx < 1 || dx > c) {
                continue;
            }

            if (home[dy][dx] == -1) {
                continue;
            }

            memorize[dy][dx] += peaceOfDust;
            memorize[y][x] -= peaceOfDust;
        }
    }

    private static int flow(int r, int c, List<Point> airPurifier, int[][] home) {
        int sumOfRemoveDust = flowOfTop(r, c, airPurifier.get(0), home);
        sumOfRemoveDust += flowOfBottom(r, c, airPurifier.get(1), home);
        return sumOfRemoveDust;
    }

    private static int flowOfTop(int r, int c, Point airPurifier, int[][] home) {
        int candidate = 0;
        Point point = new Point(airPurifier.y, airPurifier.x);

        candidate = moveRight(c, point, candidate, home, c + 1);
        candidate = moveUp(c, point, candidate, home, 0);
        candidate = moveLeft(c, point, candidate, home, 0);
        candidate = moveDown(c, point, candidate, home, airPurifier.y);

        return candidate;
    }

    private static int flowOfBottom(int r, int c, Point airPurifier, int[][] home) {
        int candidate = 0;
        Point point = new Point(airPurifier.y, airPurifier.x);

        candidate = moveRight(c, point, candidate, home, c + 1);
        candidate = moveDown(c, point, candidate, home, r + 1);
        candidate = moveLeft(c, point, candidate, home, 0);
        candidate = moveUp(c, point, candidate, home, airPurifier.y);

        return candidate;
    }

    private static int moveRight(int c, Point point, int candidate, int[][] home, int limit) {
        while (true) {
            int next = point.x + 1;
            if (next == limit) {
                break;
            }
            point.x = next;
            int temp = home[point.y][point.x];
            home[point.y][point.x] = candidate;
            candidate = temp;
        }

        return candidate;
    }

    private static int moveUp(int c, Point point, int candidate, int[][] home, int limit) {
        while (true) {
            int next = point.y - 1;
            if (next == limit) {
                break;
            }
            point.y = next;
            int temp = home[point.y][point.x];
            home[point.y][point.x] = candidate;
            candidate = temp;
        }

        return candidate;
    }

    private static int moveLeft(int c, Point point, int candidate, int[][] home, int limit) {
        while (true) {
            int next = point.x - 1;
            if (next == limit) {
                break;
            }
            point.x = next;
            int temp = home[point.y][point.x];
            home[point.y][point.x] = candidate;
            candidate = temp;
        }

        return candidate;
    }

    private static int moveDown(int c, Point point, int candidate, int[][] home, int limit) {
        while (true) {
            int next = point.y + 1;
            if (next == limit) {
                break;
            }
            point.y = next;
            int temp = home[point.y][point.x];
            home[point.y][point.x] = candidate;
            candidate = temp;
        }

        return candidate;
    }
}