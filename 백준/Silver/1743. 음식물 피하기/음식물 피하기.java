import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] condo = new int[n + 2][m + 2];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			condo[y][x] = 1;
		}

		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (condo[i][j] == 1)
					ans = Math.max(ans, dfs(condo, i, j));

		System.out.println(ans);
	}

	private static int dfs(int[][] condo, int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { y, x });
		condo[y][x] = 0;
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int dy = now[0] + move[i][0];
				int dx = now[1] + move[i][1];
				if (condo[dy][dx] == 0)
					continue;
				q.add(new int[] { dy, dx });
				condo[dy][dx] = 0;
				cnt++;
			}
		}

		return cnt;
	}
}
