import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[][] stet = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				stet[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				stet[j][i] = stet[i][j] += stet[j][i];

		boolean[] team = new boolean[n];
		int cnt = 0, idx = 0, half = n / 2;
		dfs(cnt, idx, team, stet, half, n);

		System.out.println(ans);
	}

	private static void dfs(int cnt, int idx, boolean[] team, int[][] stet, int half, int n) {
		if (cnt == half) {
			int team1 = 0;
			for (int i = 0; i < n - 1; i++) {
				if (!team[i])
					continue;
				for (int j = i + 1; j < n; j++) {
					if (!team[j])
						continue;
					team1 += stet[i][j];
				}
			}
			int team2 = 0;
			for (int i = 0; i < n - 1; i++) {
				if (team[i])
					continue;
				for (int j = i + 1; j < n; j++) {
					if (team[j])
						continue;
					team2 += stet[i][j];
				}
			}
			ans = Math.min(ans, Math.abs(team1 - team2));
			return;
		}

		for (int i = idx; i < n; i++) {
			team[i] = true;
			dfs(cnt + 1, i + 1, team, stet, half, n);
			team[i] = false;
		}
	}
}
