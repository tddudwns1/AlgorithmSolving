import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		String[][] zoo = new String[h + 4][w + 4];
		for (int i = 2; i < h + 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 2; j < w + 2; j++)
				zoo[i][j] = st.nextToken();
		}

		System.out.println(bfs(k, zoo, new boolean[h + 4][w + 4][k + 1], h + 1, w + 1));
	}

	private static int bfs(int k, String[][] zoo, boolean[][][] visited, int ey, int ex) {
		int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int[][] horse = { { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 } };
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 2, 2, 0 });
		visited[2][2][0] = true;

		int size, cnt = -1;
		while ((size = q.size()) != 0) {
			cnt++;
			while (size-- > 0) {
				int[] now = q.poll();

				if (now[0] == ey && now[1] == ex)
					return cnt;

				if (now[2] < k) {
					int nextJump = now[2] + 1;
					for (int i = 0; i < 8; i++) {
						int dy = horse[i][0] + now[0];
						int dx = horse[i][1] + now[1];

						if (zoo[dy][dx] == null)
							continue;
						if (visited[dy][dx][nextJump])
							continue;
						if (zoo[dy][dx].equals("1"))
							continue;
						q.add(new int[] { dy, dx, nextJump });
						visited[dy][dx][nextJump] = true;
					}
				}
				for (int i = 0; i < 4; i++) {
					int dy = move[i][0] + now[0];
					int dx = move[i][1] + now[1];

					if (zoo[dy][dx] == null)
						continue;
					if (visited[dy][dx][now[2]])
						continue;
					if (zoo[dy][dx].equals("1"))
						continue;
					q.add(new int[] { dy, dx, now[2] });
					visited[dy][dx][now[2]] = true;
				}
			}
		}

		return -1;
	}
}