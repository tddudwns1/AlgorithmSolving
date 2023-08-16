import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int cnt = 0, max = 0;
	static int[] dy = new int[] { 1, 0, -1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] paper = new char[n][m];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < m; j++)
				paper[i][j] = line[j * 2];
		}

		search(paper, n, m);

		System.out.println(cnt + "\n" + max);
	}

	private static void search(char[][] paper, int n, int m) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (paper[i][j] == '1')
					bfs(paper, n, m, i, j);
	}

	private static void bfs(char[][] paper, int n, int m, int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		int sum = 0;
		q.add(new int[] { y, x });
		paper[y][x] = '0';
		sum++;
		cnt++;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int moveY = now[0] + dy[i];
				int moveX = now[1] + dx[i];
				if (moveY < 0 || moveY >= n || moveX < 0 || moveX >= m)
					continue;
				if (paper[moveY][moveX] == '0')
					continue;
				q.add(new int[] { moveY, moveX });
				paper[moveY][moveX] = '0';
				sum++;
			}
		}
		max = Math.max(max, sum);
	}
}
