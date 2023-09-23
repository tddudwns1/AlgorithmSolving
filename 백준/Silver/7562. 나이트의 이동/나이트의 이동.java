import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[][] move = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

		for (int tc = 0; tc < T; tc++) {
			int len = Integer.parseInt(br.readLine());
			boolean[][] visited = new boolean[len][len];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int ty = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());

			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] { y, x, 0 });
			visited[y][x] = true;
			portal: while (true) {
				int[] now = q.poll();
				if (ty == now[0] && tx == now[1]) {
					sb.append(now[2]).append("\n");
					break portal;
				}
				for (int i = 0; i < 8; i++) {
					int dy = now[0] + move[i][0];
					if (dy < 0 || dy >= len)
						continue;
					int dx = now[1] + move[i][1];
					if (dx < 0 || dx >= len)
						continue;
					if (visited[dy][dx])
						continue;
					visited[dy][dx] = true;
					q.add(new int[] { dy, dx, now[2] + 1 });
				}
			}
		}
		System.out.println(sb);
	}
}