import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] blocks = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int count = st.countTokens();
            blocks[i] = new int[count];
            for (int j = 0; j < count; j++) {
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int MOD = 10007;
        int[] dp = new int[h + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            int[] newDp = dp.clone();
            for (int block : blocks[i]) {
                for (int j = block; j <= h; j++) {
                    newDp[j] = (newDp[j] + dp[j - block]) % MOD;
                }
            }
            dp = newDp;
        }

        System.out.println(dp[h]);
    }
}