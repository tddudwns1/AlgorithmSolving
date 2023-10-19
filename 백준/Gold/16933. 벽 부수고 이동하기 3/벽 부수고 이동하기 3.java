import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		System.out.println(bfs(map, k, n, m));
	}

	private static int bfs(char[][] map, int k, int n, int m) {
		int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0 });
		boolean[][][] visited = new boolean[n--][m--][k + 2];
		visited[0][0][0] = true;

		int size, cnt = 0;
		boolean night = true;

		while ((size = q.size()) != 0) {
			cnt++;
			night = !night;
			while (size-- > 0) {
				int[] now = q.poll();

				if (now[0] == n && now[1] == m)
					return cnt;

				for (int i = 0; i < 4; i++) {
					int dy = move[i][0] + now[0];
					if (dy < 0 || dy > n)
						continue;
					int dx = move[i][1] + now[1];
					if (dx < 0 || dx > m)
						continue;
					int broken = now[2];

					if (map[dy][dx] == '1') {
						if (broken == k)
							continue;
						if (night) {
							q.add(new int[] { now[0], now[1], broken });
							continue;
						}
						broken++;
					}
					if (visited[dy][dx][broken])
						continue;
					q.add(new int[] { dy, dx, broken });
					visited[dy][dx][broken] = true;
					visited[dy][dx][broken + 1] = true;
				}
			}
		}

		return -1;
	}
}