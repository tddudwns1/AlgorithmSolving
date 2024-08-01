import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] dp = new int[w + 2][t + 1];
        int benchmark = w + 1;

        for (int i = 1; i <= t; i++) {
            dp[0][i] += dp[0][i - 1];
            if (Integer.parseInt(br.readLine()) == 1) {
                dp[0][i]++;
                dp[benchmark][i]++;
            }
        }

        for (int time = 1; time <= t; time++) {
            int before = time - 1;
            for (int moveCount = 1; moveCount < benchmark; moveCount += 2) {
                dp[moveCount][time] = Math.max(dp[moveCount][before], dp[moveCount - 1][before]);
                if (dp[benchmark][time] == 0)
                    dp[moveCount][time]++;
            }
            for (int moveCount = 2; moveCount < benchmark; moveCount += 2) {
                dp[moveCount][time] = Math.max(dp[moveCount][before], dp[moveCount - 1][before]);
                if (dp[benchmark][time] == 1)
                    dp[moveCount][time]++;
            }
        }

        int answer = 0;
        for (int i = 0; i < benchmark; i++)
            answer = Math.max(answer, dp[i][t]);

        System.out.println(answer);
    }
}