import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp를 요구하는 문제
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 집에 벽 배치 정보
        // 한 칸씩 늘려 dp에서 고려 사항을 줄임 (y == 0 일 때, 천장이 없으면 에러)
        char[][] home = new char[n + 1][n + 1];
        for (int y = 1; y <= n; y++) {
            // gpt가 StringTokenizer + Integer.parseInt보다
            // String의 str.charAt이 더 빠를거라 해줘서 작성한 코드
            // 그냥 빠르다고 해서 사유는 모르고
            // 한 자리 숫자에 띄어쓰기로 구분되어 주어지는 경우에 유용할 듯
            // 이 문제의 경우 굳이 숫자로 할 필요 없긴 함
            String str = br.readLine();
            for (int x = 1, cnt = 0; x <= n; x++, cnt += 2)
                home[y][x] = str.charAt(cnt);
        }

        System.out.println(dp(n, home));
    }

    private static int dp(int n, char[][] home) {
        int[][][] dp = new int[n + 1][n + 1][3];
        dp[1][2][0] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 3; x <= n; x++) {
                if (home[y][x] == '1')
                    continue;

                int left = x - 1;
                int up = y - 1;

                dp[y][x][0] = dp[y][left][0]
                        + dp[y][left][1];

                dp[y][x][2] = dp[up][x][1]
                        + dp[up][x][2];

                if (home[up][x] == '1' || home[y][left] == '1')
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