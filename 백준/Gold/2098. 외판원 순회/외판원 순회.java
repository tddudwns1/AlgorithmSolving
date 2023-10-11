import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, dp;
	static int n, end, INF = 1600_0000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][1 << n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		end = (1 << n--) - 1;

		System.out.println(dfs(0, 1));
	}

	private static int dfs(int now, int visited) {
		if (visited == end) {
			if (map[now][0] == 0)
				return 1600_0001;
			return map[now][0];
		}

		if (dp[now][visited] != 0)
			return dp[now][visited];

		int min = INF;
		for (int i = n; i >= 0; i--) {
			if ((visited & (1 << i)) > 0)
				continue;
			if (map[now][i] == 0)
				continue;
			min = Math.min(min, map[now][i] + dfs(i, visited | (1 << i)));
		}
		
		return dp[now][visited] = min;
	}
}