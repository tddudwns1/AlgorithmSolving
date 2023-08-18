import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int max = 0, r, c;
	static int[] moveY = new int[] { 1, 0, -1, 0 };
	static int[] moveX = new int[] { 0, 1, 0, -1 };
	static Character[][] board;
	static Set<Character> set = new HashSet<>();
	static boolean[] visited = new boolean[26];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new Character[r + 2][c + 2];
		for (int i = 1; i <= r; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 1; j <= c; j++) {
				board[i][j] = line[j - 1];
			}
		}

		visited[board[1][1] - 'A'] = true;
		dfs(1, 1, 1);

		System.out.println(max);
	}

	private static void dfs(int y, int x, int len) {
		for (int i = 0; i < 4; i++) {
			int dy = y + moveY[i];
			int dx = x + moveX[i];
			if(dy < 1 || dx < 1 || dy > r || dx > c)
				continue;
			
			Character ch = board[dy][dx];
			if (!visited[ch - 'A']) {
				visited[ch - 'A'] = true;
				dfs(dy, dx, len + 1);
				visited[ch - 'A'] = false;
			}
		}
		max = Math.max(max, len);
	}
}
