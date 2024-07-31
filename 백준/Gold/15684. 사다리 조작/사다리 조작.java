import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean flag = false;

    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        boolean[][] ladder = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ladder[y][x] = true;
        }

        if (checkOddLine(ladder, n, h) <= 3)
            for (int limit = m % 2; limit < 4; limit += 2) {
                dfs(n, h, 1, 1, 0, limit, ladder);

                if (flag) {
                    answer = limit;
                    break;
                }
            }

        System.out.println(answer);
    }

    private static int checkOddLine(boolean[][] ladder, int n, int h) {
        int countOdd = 0;
        for (int x = 1; x < n; x++) {
            boolean checkOdd = false;
            for (int y = 1; y <= h; y++)
                if (ladder[y][x])
                    checkOdd = !checkOdd;
            
            if (checkOdd)
                countOdd++;
        }

        return countOdd;
    }

    private static void dfs(int n, int h, int startY, int startX, int count, int limit, boolean[][] ladder) {
        if (flag)
            return;

        if (count == limit) {
            if (!checkVerticalLine(n, h, ladder))
                return;

            flag = true;
            return;
        }

        for (int x = startX; x < n; x++)
            callDfs(n, h, startY, x, count, limit, ladder);

        for (int y = startY + 1; y <= h; y++)
            for (int x = 1; x < n; x++)
                callDfs(n, h, y, x, count, limit, ladder);
    }

    private static void callDfs(int n, int h, int y, int x, int count, int limit, boolean[][] ladder) {
        if (ladder[y][x] || ladder[y][x - 1] || ladder[y][x + 1])
            return;

        ladder[y][x] = true;
        dfs(n, h, y, x, count + 1, limit, ladder);
        ladder[y][x] = false;
    }

    private static boolean checkVerticalLine(int n, int h, boolean[][] ladder) {
        for (int x = 1; x <= n; x++) {
            int nowX = x;
            int nowY = 0;

            while (++nowY <= h) {
                if (ladder[nowY][nowX])
                    nowX++;
                else if (ladder[nowY][nowX - 1])
                    nowX--;
            }

            if (nowX != x)
                return false;
        }

        return true;
    }
}