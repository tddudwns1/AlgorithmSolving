import java.util.*;
import java.io.*;

public class Main {
	static long answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= t; testCase++) {

			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if (n == m) {
				sb.append(1).append("\n");
				continue;
			}
			
			int[][] dp = new int[m+1][m+1];
			
			dp[0][0] = 1;
			dp[1][0] = 1;
			
			for(int i = 1; i <= m; i++) {
				dp[i][0] = 1;
				dp[i][i] = 1;
				for(int j = 1; j<i; j++) {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
			
			sb.append(dp[m][n]).append("\n");
		}
		
		System.out.println(sb);

	}

}
