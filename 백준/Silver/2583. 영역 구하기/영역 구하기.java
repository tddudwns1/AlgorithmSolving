import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] move = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		boolean[][] graphPaper = new boolean[m][n];
		Queue<Integer> section = new PriorityQueue<>();
		for (int tc = 0; tc < k; tc++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int i = y1; i < y2; i++)
				for (int j = x1; j < x2; j++)
					graphPaper[i][j] = true;
		}

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (!graphPaper[i][j])
					section.add(bfs(i, j, m, n, graphPaper));

		sb.append(section.size()).append("\n");
		while(!section.isEmpty())
			sb.append(section.poll()).append(" ");
		System.out.println(sb);
	}

	private static int bfs(int y, int x, int m, int n, boolean[][] graphPaper) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { y, x });
		graphPaper[y][x] = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int dy = now[0] + move[i][0];
				if (dy < 0 || dy >= m)
					continue;
				int dx = now[1] + move[i][1];
				if (dx < 0 || dx >= n)
					continue;
				if(graphPaper[dy][dx])
					continue;
				graphPaper[dy][dx] = true;
				cnt++;
				q.add(new int[] { dy, dx });
			}
		}
		return cnt;
	}
}