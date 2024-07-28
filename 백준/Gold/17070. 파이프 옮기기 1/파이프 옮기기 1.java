import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] home = new int[n + 1][n + 1];
        for (int y = 1; y <= n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= n; x++)
                home[y][x] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dp(n, home));
    }

    private static int dp(int n, int[][] home) {
        int[][][] dp = new int[n + 1][n + 1][3];
        dp[1][2][0] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 3; x <= n; x++) {
                if (home[y][x] == 1)
                    continue;
                
                int left = x - 1;
                int up = y - 1;

                dp[y][x][0] = dp[y][left][0]
                        + dp[y][left][1];

                dp[y][x][2] = dp[up][x][1]
                        + dp[up][x][2];

                if (home[up][x] == 1 || home[y][left] == 1)
                    continue;

                dp[y][x][1] = dp[up][left][0]
                        + dp[up][left][1]
                        + dp[up][left][2];
            }
        }

        return dp[n][n][0]
                + dp[n][n][1]
                + dp[n][n][2];
    }
}