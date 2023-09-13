import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Double> set;
	static int[][] board, move;
//	static int[] num;

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
//		num = new int[6];

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				dfs(0, 0, i, j);
		System.out.println(set.size());
	}

	private static void dfs(double num, int def, int y, int x) {
		if (def == 6) {
			set.add(num);
			return;
		}
		num += board[y][x] * Math.pow(10, def);;
		for(int i = 0; i < 4; i++) {
			int dy = y + move[i][0];
			if(dy < 0 || dy >= 5) continue;
			int dx = x + move[i][1];
			if(dx < 0 || dx >= 5) continue;
			dfs(num, def + 1, dy, dx);
		}
	}
}
