import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 10_0000_0000, LAST = (1 << 10) - 1;
	private static Long[][][] dp;

	private static long solve(int n, int usedNumbers, int length, int lastNumber) {
		usedNumbers |= (1 << lastNumber);
		if (++length == n) {
			if (usedNumbers == LAST)
				return 1;
			return 0;
		}

		if (dp[length][lastNumber][usedNumbers] != null)
			return dp[length][lastNumber][usedNumbers];
		
		long result = 0;
		if (lastNumber > 0)
			result += solve(n, usedNumbers, length, lastNumber - 1) % MOD;
		if (lastNumber < 9)
			result += solve(n, usedNumbers, length, lastNumber + 1) % MOD;
		return dp[length][lastNumber][usedNumbers] = result % MOD;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new Long[n][10][1 << 10];

		long result = 0;
		for (int i = 1; i < 10; ++i) 
			result += solve(n, 1 << i, 0, i) % MOD;

		System.out.println(result % MOD);
	}
}