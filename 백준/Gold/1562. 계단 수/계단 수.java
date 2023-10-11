import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		
		int MOD = 1000000000;
		int num = Integer.parseInt(buf.readLine());
		int[][][] memo = new int[num][10][4];
		
		memo[0][0][0] = 1;
		for (int i = 1; i < 9; i ++) {
			memo[0][i][1] = 1;
		}
		memo[0][9][2] = 1;
		
		for (int i = 1; i < num; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					memo[i][0][3] = (memo[i-1][1][3] + memo[i-1][1][2]) % MOD;
					memo[i][0][0] = (memo[i-1][1][0] + memo[i-1][1][1]) % MOD;
				}
				else if (j == 9) {
					memo[i][9][3] = (memo[i-1][8][0] + memo[i-1][8][3]) % MOD;
					memo[i][9][2] = (memo[i-1][8][1] + memo[i-1][8][2]) % MOD;
				}
				else {
					memo[i][j][0] = (memo[i-1][j-1][0] + memo[i-1][j+1][0]) % MOD;
					memo[i][j][1] = (memo[i-1][j-1][1] + memo[i-1][j+1][1]) % MOD;
					memo[i][j][2] = (memo[i-1][j-1][2] + memo[i-1][j+1][2]) % MOD;
					memo[i][j][3] = (memo[i-1][j-1][3] + memo[i-1][j+1][3]) % MOD;
				}
			}
		}
		
		int result = 0;
		
		for (int i = 1; i < 10; i++) {
			result = (result + memo[num-1][i][3]) % MOD;
		}
		
		System.out.println(result);
	}
}