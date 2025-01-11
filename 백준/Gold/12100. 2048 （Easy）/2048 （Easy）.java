import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int x = 0; x < n; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        process(0, n, board);

        System.out.println(answer);

    }

    private static void process(int count, int n, int[][] board) {
        int max = getMax(n, board);

        if (answer / max > (6 - count++)) {
            return;
        }

        if (count > 5) {
            answer = Math.max(answer, max);
            return;
        }

        process(count, n, pushRight(n, board));
        process(count, n, pushDown(n, board));
        process(count, n, pushLeft(n, board));
        process(count, n, pushUp(n, board));
    }

    private static int[][] pushRight(int n, int[][] board) {
        int[][] afterPush = new int[n][n];
        for (int y = 0; y < n; y++) {
            Queue<Integer> q = new ArrayDeque<>();
            int before = 0;
            for (int x = n - 1; x >= 0; x--) {
                int now = board[y][x];
                if (now != 0) {
                    if (before == 0) {
                        before = now;
                    } else {
                        if (before == now) {
                            q.add(now << 1);
                            before = 0;
                        } else {
                            q.add(before);
                            before = now;
                        }
                    }
                }
            }
            if (before != 0) {
                q.add(before);
            }
            int x = n - 1;
            while (!q.isEmpty()) {
                int now = q.poll();

                afterPush[y][x--] = now;
            }
        }

        return afterPush;
    }

    private static int[][] pushLeft(int n, int[][] board) {
        int[][] afterPush = new int[n][n];
        for (int y = 0; y < n; y++) {
            Queue<Integer> q = new ArrayDeque<>();
            int before = 0;
            for (int x = 0; x < n; x++) {
                int now = board[y][x];
                if (now != 0) {
                    if (before == 0) {
                        before = now;
                    } else {
                        if (before == now) {
                            q.add(now << 1);
                            before = 0;
                        } else {
                            q.add(before);
                            before = now;
                        }
                    }
                }
            }
            if (before != 0) {
                q.add(before);
            }
            int x = 0;
            while (!q.isEmpty()) {
                int now = q.poll();

                afterPush[y][x++] = now;
            }
        }

        return afterPush;
    }

    private static int[][] pushDown(int n, int[][] board) {
        int[][] afterPush = new int[n][n];
        for (int x = 0; x < n; x++) {
            Queue<Integer> q = new ArrayDeque<>();
            int before = 0;
            for (int y = n - 1; y >= 0; y--) {
                int now = board[y][x];
                if (now != 0) {
                    if (before == 0) {
                        before = now;
                    } else {
                        if (before == now) {
                            q.add(now << 1);
                            before = 0;
                        } else {
                            q.add(before);
                            before = now;
                        }
                    }
                }
            }
            if (before != 0) {
                q.add(before);
            }
            int y = n - 1;
            while (!q.isEmpty()) {
                int now = q.poll();

                afterPush[y--][x] = now;
            }
        }

        return afterPush;
    }

    private static int[][] pushUp(int n, int[][] board) {
        int[][] afterPush = new int[n][n];
        for (int x = 0; x < n; x++) {
            Queue<Integer> q = new ArrayDeque<>();
            int before = 0;
            for (int y = 0; y < n; y++) {
                int now = board[y][x];
                if (now != 0) {
                    if (before == 0) {
                        before = now;
                    } else {
                        if (before == now) {
                            q.add(now << 1);
                            before = 0;
                        } else {
                            q.add(before);
                            before = now;
                        }
                    }
                }
            }
            if (before != 0) {
                q.add(before);
            }
            int y = 0;
            while (!q.isEmpty()) {
                int now = q.poll();

                afterPush[y++][x] = now;
            }
        }

        return afterPush;
    }

    private static int getMax(int n, int[][] board) {
        int max = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                max = Math.max(max, board[y][x]);
            }
        }
        return max;
    }
}