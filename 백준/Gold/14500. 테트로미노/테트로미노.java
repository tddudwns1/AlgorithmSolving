import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, paper;
    static int max_num = 0, n, m;
    static int[][] record = new int[3][2];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int now = paper[i][j];
                paper[i][j] = 0;
                dfs(0, now, i, j);
            }
        }

        System.out.println(max_num);
    }

    private static void dfs(int depth, int sum, int y, int x) {
        record[depth] = new int[]{y, x};

        if (depth == 2) {
            getMaxNum(sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dy = y + move[i][0];
            int dx = x + move[i][1];
            if (paper[dy][dx] == 0)
                continue;
            int now = paper[dy][dx];
            paper[dy][dx] = 0;
            dfs(depth + 1, sum + now, dy, dx);
            paper[dy][dx] = now;
        }
    }

    private static void getMaxNum(int sum) {
        int max = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 4; j++) {
                int dy = record[i][0] + move[j][0];
                int dx = record[i][1] + move[j][1];
                max = Math.max(max, paper[dy][dx]);
            }
        }

        max_num = Math.max(max_num, sum + max);
    }
}