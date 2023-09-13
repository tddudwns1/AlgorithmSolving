import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board = new int[21][21];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int ans = -1;
		int color = 0;
		for (int i = 0; i < 9; i++) {
			color = color != 1 ? 1 : 2;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			board[y][x] = color;
		}
		for (int i = 9; i < n; i++) {
			color = color != 1 ? 1 : 2;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			board[y][x] = color;
			if (check(y, x, color)) {
				ans = i + 1;
				break;
			}
		}
		System.out.println(ans);
	}

	private static boolean check(int y, int x, int color) {
		if (check가로(y, x, color))
			return true;
		if (check세로(y, x, color))
			return true;
		if (check우상(y, x, color))
			return true;
		if (check좌상(y, x, color))
			return true;
		return false;
	}

	private static boolean check좌상(int y, int x, int color) {
		int cnt = 1;
		int tmp = x;
		int tmp2 = y;
		while (board[--tmp2][--tmp] == color)
			cnt++;
		while (board[++y][++x] == color)
			cnt++;
		if (cnt == 5)
			return true;
		return false;
	}

	private static boolean check우상(int y, int x, int color) {
		int cnt = 1;
		int tmp = x;
		int tmp2 = y;
		while (board[--tmp2][++tmp] == color)
			cnt++;
		while (board[++y][--x] == color)
			cnt++;
		if (cnt == 5)
			return true;
		return false;
	}

	private static boolean check세로(int y, int x, int color) {
		int cnt = 1;
		int tmp = y;
		while (board[--tmp][x] == color)
			cnt++;
		while (board[++y][x] == color)
			cnt++;
		if (cnt == 5)
			return true;
		return false;
	}

	private static boolean check가로(int y, int x, int color) {
		int cnt = 1;
		int tmp = x;
		while (board[y][--tmp] == color)
			cnt++;
		while (board[y][++x] == color)
			cnt++;
		if (cnt == 5)
			return true;
		return false;
	}
}