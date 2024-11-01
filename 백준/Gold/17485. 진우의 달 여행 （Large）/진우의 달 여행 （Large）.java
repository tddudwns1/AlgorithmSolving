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

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());

            int next = y + 1;

            for (int i = 0; i < 3; i++){
                memo[next][0][i] = 1000000;
                memo[next][limit][i] = 1000000;
            }

            for (int x = 1; x <= m; x++) {
                int now = Integer.parseInt(st.nextToken());
                
                int left = x - 1;
                int right = x + 1;

                memo[next][x][0] = Math.min(memo[y][left][1], memo[y][left][2]) + now;
                memo[next][x][1] = Math.min(memo[y][x][0], memo[y][x][2]) + now;
                memo[next][x][2] = Math.min(memo[y][right][0], memo[y][right][1]) + now;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= m; i++)
            for (int j = 0; j < 3; j++)
                answer = Math.min(answer, memo[n][i][j]);

        System.out.println(answer);
    }
}