import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int len;
	static int[] move;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String srt = br.readLine();
		len = srt.length() / 2;
		StringTokenizer st = new StringTokenizer(srt);
		move = new int[len];
		for (int i = 0; i < len; i++)
			move[i] = Integer.parseInt(st.nextToken());

		dp = new int[5][5][len + 1];

		System.out.println(solve(0, 0, 0));
	}

	static int solve(int left, int right, int cnt) {
		if (cnt == len)
			return 0;

		if (dp[left][right][cnt] != 0)
			return dp[left][right][cnt];

		return dp[left][right][cnt] = Math.min(solve(move[cnt], right, cnt + 1) + energy(left, move[cnt]),
				solve(left, move[cnt], cnt + 1) + energy(right, move[cnt]));
	}

	static int energy(int pos, int des) {
		if (pos == 0)
			return 2;
		if (pos == des)
			return 1;
		if (Math.abs(pos - des) == 2)
			return 4;
		else
			return 3;
	}
}