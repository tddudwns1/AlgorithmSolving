import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		System.out.println(bfs(map, n, m));
	}

	private static int bfs(char[][] map, int n, int m) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][2];
		int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int ans = -1;
		q.add(new int[] { 0, 0, 0, 1 });
		visited[0][0][0] = true;
		int ey = n - 1, ex = m - 1;

		portal: while (!q.isEmpty()) {
			int[] now = q.poll();
			int y = now[0], x = now[1], broken = now[2], cnt = now[3];
			if (y == ey && x == ex) {
				ans = cnt;
				break portal;
			}
			for (int i = 0; i < 4; i++) {
				int dy = y + move[i][0];
				if (dy < 0 || dy >= n)
					continue;
				int dx = x + move[i][1];
				if (dx < 0 || dx >= m)
					continue;
				if (visited[dy][dx][broken])
					continue;
				int nowBroken = broken;
				if (map[dy][dx] == '1')
					if (nowBroken == 1)
						continue;
					else
						nowBroken++;
				visited[dy][dx][nowBroken] = true;
				q.add(new int[] { dy, dx, nowBroken, cnt + 1 });
			}
		}
		return ans;
	}
}