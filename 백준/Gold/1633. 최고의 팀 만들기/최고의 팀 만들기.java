import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[16][16];

        while (true) {
            String line = br.readLine();
            if (line == null || line.isEmpty())
                break;

            StringTokenizer st = new StringTokenizer(line);
            int white = Integer.parseInt(st.nextToken());
            int black = Integer.parseInt(st.nextToken());

            for (int w = 15; w >= 0; w--) {
                for (int b = 15; b >= 0; b--) {
                    if (w > 0)
                        dp[w][b] = Math.max(dp[w][b], dp[w - 1][b] + white);

                    if (b > 0)
                        dp[w][b] = Math.max(dp[w][b], dp[w][b - 1] + black);

                }
            }
        }

        System.out.println(dp[15][15]);
    }
}