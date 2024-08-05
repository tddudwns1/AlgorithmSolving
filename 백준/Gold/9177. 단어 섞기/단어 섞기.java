import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final String HEAD = "Data set ";
    static final String YES = ": yes\n";
    static final String NO = ": no\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            sb.append(HEAD).append(i);

            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] s1 = st.nextToken().toCharArray();
            char[] s2 = st.nextToken().toCharArray();
            char[] s3 = st.nextToken().toCharArray();

            int s1len = s1.length;
            int s2len = s2.length;
            int min = Math.min(s1len, s2len);

            boolean[][] dp = new boolean[s1len + 1][s2len + 1];
            dp[0][0] = true;
            for (int x = 0; x < s2len; x++)
                if (s2[x] == s3[x])
                    dp[0][x + 1] = true;
                else
                    break;

            for (int y = 0; y < s1len; y++)
                if (s1[y] == s3[y])
                    dp[y + 1][0] = true;
                else
                    break;

            int before = 0;
            for (int depth = 1; depth <= min; depth++) {
                if (dp[depth][before] && s2[before] == s3[depth * 2 - 1])
                    dp[depth][depth] = true;
                else if (dp[before][depth] && s1[depth - 1] == s3[depth * 2 - 1])
                    dp[depth][depth] = true;

                for (int x = 1; x <= s2len; x++)
                    if (dp[depth][x - 1] && s2[x - 1] == s3[depth + x - 1])
                        dp[depth][x] = true;
                    else if (dp[depth - 1][x] && s1[depth - 1] == s3[depth + x - 1])
                        dp[depth][x] = true;

                for (int y = 1; y <= s1len; y++)
                    if (dp[y - 1][depth] && s1[y - 1] == s3[depth + y - 1])
                        dp[y][depth] = true;
                    else if (dp[y][before] && s2[before] == s3[depth + y - 1])
                        dp[y][depth] = true;

                before = depth;
            }

            if (dp[s1len][s2len])
                sb.append(YES);
            else
                sb.append(NO);
        }

        System.out.println(sb);
    }
}