import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] apples = new int[r + 2][c + 2];
        int[][] bananas = new int[r + 2][c + 2];

        for (int y = 1; y <= r; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 1; x <= c; x++) {
                String now = st.nextToken();
                char fruit = now.charAt(0);
                int count = Integer.parseInt(now.substring(1));

                if (fruit == 'A')
                    apples[y][x] = count;
                else
                    bananas[y][x] = count;
            }
        }

        prefixSum(r, c, apples, bananas);

        System.out.println(dp(r, c, apples, bananas));
    }

    private static void prefixSum(int r, int c, int[][] apples, int[][] bananas) {
        for (int x = 1; x <= c; x++)
            for (int y = r - 1; y > 1; y--)
                apples[y][x] += apples[y + 1][x];

        for (int y = 1; y <= r; y++)
            for (int x = c - 1; x > 1; x--)
                bananas[y][x] += bananas[y][x + 1];
    }

    private static int dp(int r, int c, int[][] apples, int[][] bananas) {
        int[][] dp = new int[r + 1][c + 1];

        /**
         *      1. 오
         *          밑으로 다 더하기(사과)
         *      2. 밑
         *          오른쪽으로 다 더하기(바나나)
         *      3. 오밑
         *          위에 2개 다 진행
         */

        for (int y = 1; y <= r; y++) {
            for (int x = 1; x <= c; x++) {
                dp[y][x] =
                        Math.max(dp[y - 1][x] + bananas[y - 1][x + 1],
                                Math.max(dp[y][x - 1] + apples[y + 1][x - 1],
                                        dp[y - 1][x - 1] + bananas[y - 1][x] + apples[y][x - 1]
                                )
                        );
            }
        }

        return dp[r][c];
    }
}