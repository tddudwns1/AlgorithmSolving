import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max = 0, r, c;
	static int[] moveY = new int[] { 1, 0, -1, 0 };
	static int[] moveX = new int[] { 0, 1, 0, -1 };
	static char[][] board;
	static boolean[] visited = new boolean[26];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][c];
		for (int i = 0; i < r; i++) 
			board[i] = br.readLine().toCharArray();

		visited[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(max);
	}

	private static void dfs(int y, int x, int len) {
		for (int i = 0; i < 4; i++) {
			int dy = y + moveY[i];
			int dx = x + moveX[i];
			if (dy < 0 || dx < 0 || dy >= r || dx >= c)
				continue;

			char ch = board[dy][dx];
			if (!visited[ch - 'A']) {
				visited[ch - 'A'] = true;
				dfs(dy, dx, len + 1);
				visited[ch - 'A'] = false;
			}
		}
		max = Math.max(max, len);
	}
}
