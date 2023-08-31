import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[75][75];
		
		for(int i = 0; i<dp.length; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			for(int j = 1; j<i; j++) {
				dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
			}
		}
		
		for(int testCase = 1; testCase <= t; testCase++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[10+n-1][n]).append("\n");
		}
		System.out.println(sb);
	}
}
