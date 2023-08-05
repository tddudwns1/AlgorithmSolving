import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int x = 0;
		int y = 0;
		char[][] campus = new char[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++) {
				campus[i][j] = input[j - 1];
				if (input[j - 1] == 'I') {
					x = i;
					y = j;
				}
			}
		}
		bfs(campus, x, y);
	}

	private static void bfs(char[][] campus, int x, int y) {
		boolean[][] visited = new boolean[campus.length][campus[0].length];
		Queue<int[]> q = new LinkedList<>();
		int[][] move = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		q.add(new int[] { x, y });
		visited[x][y] = true;
		int cnt = 0;
		int sum = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int dx = now[0] + move[i][0];
				int dy = now[1] + move[i][1];
				if (!visited[dx][dy]) {
					if (campus[dx][dy] == 'P') {
						sum++;
						cnt++;
						visited[dx][dy] = true;
						q.add(new int[] { dx, dy });
					} else if (campus[dx][dy] == 'O') {
						sum++;
						visited[dx][dy] = true;
						q.add(new int[] { dx, dy });
					}
				}
			}
		}
		if (cnt == 0)
			System.out.println("TT");
		else
			System.out.println(cnt);
	}
}
