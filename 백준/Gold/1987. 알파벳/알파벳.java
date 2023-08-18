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
	static int max = 0;
	static int[] moveY = new int[] { 1, 0, -1, 0 };
	static int[] moveX = new int[] { 0, 1, 0, -1 };
	static Character[][] board;
	static Set<Character> set = new HashSet<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		board = new Character[r + 2][c + 2];
		for (int i = 1; i <= r; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 1; j <= c; j++) {
				board[i][j] = line[j - 1];
			}
		}

		set.add(board[1][1]);
		set.add(null);
		dfs(1, 1, 1);

		System.out.println(max);
	}

	private static void dfs(int y, int x, int len) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int dy = y + moveY[i];
			int dx = x + moveX[i];
			Character ch = board[dy][dx];
			if (!set.contains(ch)) {
				cnt++;
				set.add(ch);
				dfs(dy, dx, len + 1);
				set.remove(ch);
			}
		}
		max = Math.max(max, len);
	}
}
