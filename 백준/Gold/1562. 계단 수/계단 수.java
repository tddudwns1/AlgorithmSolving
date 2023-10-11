import java.io.*;
import java.util.*;

public class Main {
	private static final int MOD = 1000000000;

	private static Long[][][] cache;

	private static long solve(int N, int usedNumbers, int length, int lastNumber) {
		usedNumbers |= (1 << lastNumber);
		if (length == N) {
			if (usedNumbers == ((1 << 10) - 1) && lastNumber != 0) {
				return 1;
			} else {
				return 0;
			}
		}

		if (cache[usedNumbers][length][lastNumber] == null) {
			long result = 0;

			if (lastNumber > 0) {
				result += solve(N, usedNumbers, length + 1, lastNumber - 1) % MOD;
			}

			if (lastNumber < 9) {
				result += solve(N, usedNumbers, length + 1, lastNumber + 1) % MOD;
			}

			cache[usedNumbers][length][lastNumber] = result % MOD;
		}

		return cache[usedNumbers][length][lastNumber];
	}

	private static long solve(int N) {
		long result = 0;

		for (int i = 0; i < 10; ++i) {
			result += solve(N, 1 << i, 1, i) % MOD;
		}

		return result % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		cache = new Long[1 << 10][N][10];

		System.out.println(solve(N));

		in.close();
	}
}