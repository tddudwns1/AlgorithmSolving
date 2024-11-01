import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][][] memo = new int[n + 1][m + 2][3];

        int limit = m + 1;

        for (int i = 0; i < 3; i++){
            memo[0][0][i] = 1000000;
            memo[0][limit][i] = 1000000;
        }

        for (int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 3; i++){
                memo[y][0][i] = 1000000;
                memo[y][limit][i] = 1000000;
            }

            for (int x = 1; x <= m; x++) {
                int now = Integer.parseInt(st.nextToken());

                memo[y][x][0] = Math.min(memo[y - 1][x - 1][1], memo[y - 1][x - 1][2]) + now;
                memo[y][x][1] = Math.min(memo[y - 1][x][0], memo[y - 1][x][2]) + now;
                memo[y][x][2] = Math.min(memo[y - 1][x + 1][0], memo[y - 1][x + 1][1]) + now;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= m; i++)
            for (int j = 0; j < 3; j++)
                answer = Math.min(answer, memo[n][i][j]);

        System.out.println(answer);
    }
}