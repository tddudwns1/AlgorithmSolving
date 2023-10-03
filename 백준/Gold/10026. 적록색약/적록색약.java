import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Main {
	static int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		char[][] sector = new char[n][n];
		for (int i = 0; i < n; i++)
			sector[i] = br.readLine().toCharArray();

		int nomal = 0, patient = 0;
		nomal += patient += explore('B', '1', sector, n);
		nomal += explore('R', '2', sector, n) + explore('G', '2', sector, n);
		patient += explore('2', '3', sector, n);
		
		StringBuilder sb = new StringBuilder();
		System.out.println(sb.append(nomal).append(" ").append(patient));
	}

	private static int explore(char target, char change, char[][] sector, int n) {
		int cnt = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (sector[i][j] == target)
					cnt += bfs(target, change, sector, n, i, j);
		return cnt;
	}

	private static int bfs(char target, char change, char[][] sector, int n, int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { y, x });
		sector[y][x] = change;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			for(int i = 0; i < 4; i++) {
				int dy = now[0] + move[i][0];
				if(dy < 0 || dy >= n)
					continue;
				int dx = now[1] + move[i][1];
				if(dx < 0 || dx >= n)
					continue;
				if(sector[dy][dx] != target)
					continue;
				q.add(new int[] { dy, dx });
				sector[dy][dx] = change;
			}
		}

		return 1;
	}
}
