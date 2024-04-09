import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, paper;
    static int max_sum = 0, max_num = 0, n, m;
    static int[][] record = new int[4][2];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++)
                max_num = Math.max(paper[i][j] = Integer.parseInt(st.nextToken()), max_num);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int now = paper[i][j];
                paper[i][j] = 0;
                dfs(3, now, i, j);
            }
        }

        System.out.println(max_sum);
    }

    private static void dfs(int depth, int sum, int y, int x) {
        if(sum + max_num * depth < max_sum)
            return;

        record[depth] = new int[]{y, x};

        if (depth == 1) {
            getMaxNum(sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dy = y + move[i][0];
            int dx = x + move[i][1];
            if (paper[dy][dx] == 0)
                continue;

            int now = paper[dy][dx];
            int next = sum + now;

            //if(next + max_num * (4 - depth) < max_sum)
            //    continue;

            paper[dy][dx] = 0;
            dfs(depth - 1, next, dy, dx);
            paper[dy][dx] = now;
        }
    }

    private static void getMaxNum(int sum) {
        int max = 0;
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                int dy = record[i][0] + move[j][0];
                int dx = record[i][1] + move[j][1];

                if(paper[dy][dx] == 0)
                    continue;

                max = Math.max(max, paper[dy][dx]);
            }
        }

        max_sum = Math.max(max_sum, sum + max);
    }
}