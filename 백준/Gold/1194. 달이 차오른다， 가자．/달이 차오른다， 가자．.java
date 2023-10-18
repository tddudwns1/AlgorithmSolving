import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Minsik {
		int y;
		int x;
		int move;
		int keys;

		public Minsik(int y, int x, int move, int keys) {
			this.y = y;
			this.x = x;
			this.move = move;
			this.keys = keys;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		boolean[][][] visited = new boolean[n + 2][m + 2][64];
		char[][] maze = new char[n + 2][m + 2];
		Minsik now = null;
		for (int i = 1; i <= n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++)
				if ('0' == (maze[i][j] = line[j - 1]))
					now = new Minsik(i, j, 0, 0);
		}
		bfs(visited, maze, now);
	}

	private static void bfs(boolean[][][] visited, char[][] maze, Minsik now) {
		int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<Minsik> q = new ArrayDeque<>();
		q.add(now);
		visited[now.y][now.x][0] = true;
		maze[now.y][now.x] = '.';

		while (!q.isEmpty()) {
			now = q.poll();
			for (int i = 0; i < 4; i++) {
				int dy = now.y + move[i][0];
				int dx = now.x + move[i][1];

				if (visited[dy][dx][now.keys])
					continue;
				if (maze[dy][dx] == 0 || maze[dy][dx] == '#')
					continue;

				if (maze[dy][dx] == '.') {
					q.add(new Minsik(dy, dx, now.move + 1, now.keys));
					visited[dy][dx][now.keys] = true;
				} else if (maze[dy][dx] >= 'a') {
					int keys = now.keys | (1 << (maze[dy][dx] - 97));
					q.add(new Minsik(dy, dx, now.move + 1, keys));
					visited[dy][dx][keys] = true;
				} else if (maze[dy][dx] >= 'A') {
					if ((now.keys & (1 << (maze[dy][dx] - 65))) == 0)
						continue;
					q.add(new Minsik(dy, dx, now.move + 1, now.keys));
					visited[dy][dx][now.keys] = true;
				} else {
					System.out.println(now.move + 1);
					return;
				}
			}
		}

		System.out.println(-1);
	}
}