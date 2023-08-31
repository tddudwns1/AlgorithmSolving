import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] puzzle;
	static int[] row, col, sec;
	static int check = 1 << 9 - 1;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		puzzle = new char[81];
		// 0 가로 1 세로 2 네모
		row = new int[9];
		col = new int[9];
		sec = new int[9];

		for (int i = 0; i < 9; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				puzzle[i * 9 + j] = line[j];
				if (line[j] == '0')
					continue;
				row[i] |= 1 << (line[j] - '0' - 1);
				col[j] |= 1 << (line[j] - '0' - 1);
				sec[(i / 3) * 3 + j / 3] |= 1 << (line[j] - '0' - 1);
			}
		}

		bt(0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				sb.append(puzzle[i * 9 + j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void bt(int cnt) {
		if (cnt == 81) {
			flag = true;
			return;
		}
		if (puzzle[cnt] != '0')
			bt(cnt + 1);
		else {
			int y = cnt / 9;
			int x = cnt % 9;
			int yx = (y / 3) * 3 + x / 3;
			for (int i = 0; i < 9; i++) {
				int n = 1 << i;
				if ((row[y] & n) != 0 || (col[x] & n) != 0 || (sec[yx] & n) != 0)
					continue;
				row[y] |= n;
				col[x] |= n;
				sec[yx] |= n;
				puzzle[y * 9 + x] = (char) (i + 1 + '0');
				bt(cnt + 1);
				if (flag)
					return;
				row[y] &= ~n;
				col[x] &= ~n;
				sec[(y / 3) * 3 + x / 3] &= ~n;
				puzzle[y * 9 + x] = '0';
			}
		}
	}
}