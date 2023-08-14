import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Integer> set;
	static int[][] board, move;
	static int[] num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		set = new HashSet<>();
		board = new int[5][5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		move = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		num = new int[6];

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				dfs(0, i, j);
		System.out.println(set.size());
	}

	private static void dfs(int def, int y, int x) {
		if (def == 6) {
			int ans = 0;
			for (int i = 0; i < 6; i++)
				ans += num[i] * Math.pow(10, i);

			set.add(ans);
			return;
		}
		num[def] = board[y][x];
		for(int i = 0; i < 4; i++) {
			int dy = y + move[i][0];
			if(dy < 0 || dy >= 5) continue;
			int dx = x + move[i][1];
			if(dx < 0 || dx >= 5) continue;
			dfs(def + 1, dy, dx);
		}
	}
}
