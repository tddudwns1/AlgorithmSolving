import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		String[][] plate = new String[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++)
				plate[i][j] = st.nextToken();
		}

		int[] ans = bfs(plate, n, m);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}

	private static int[] bfs(String[][] plate, int n, int m) {
		Queue<int[]> q = new LinkedList<>();
		int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int hours = 0, remain = 0;

		while (true) {
			Queue<int[]> candidate = new LinkedList<>();
			boolean[][] visited = new boolean[n + 2][m + 2];
			q.add(new int[] { 1, 1 });
			visited[1][1] = true;

			while (!q.isEmpty()) {
				int[] now = q.poll();
				for (int i = 0; i < 4; i++) {
					int dy = now[0] + move[i][0];
					int dx = now[1] + move[i][1];
					if (visited[dy][dx])
						continue;
					if (plate[dy][dx] == null)
						continue;
					if (plate[dy][dx].equals("0"))
						q.add(new int[] { dy, dx });
					else
						candidate.add(new int[] { dy, dx });
					visited[dy][dx] = true;
				}
			}
			if (candidate.isEmpty())
				break;
			remain = candidate.size();
			hours++;

			while (!candidate.isEmpty()) {
				int[] now = candidate.poll();
				plate[now[0]][now[1]] = "0";
			}
		}

		return new int[] { hours, remain };
	}

}