import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max = 0, r, c;
	static int[] dy = new int[] { -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int[][] space = new int[r + 2][c + 2];
		for (int i = 1; i <= r; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 1; j <= c; j++)
				if (line[j - 1] == '.')
					space[i][j] = 1;
				else
					space[i][j] = 2;
		}
		for (int i = 1; i <= r; i++)
			putPipeLine(i, 1, space);
		System.out.println(max);
	}

	static int putPipeLine(int y, int x, int[][] space) {
		int flag = 0;
		if (x == c) {
			max++;
			return 1;
		}
		int nextX = x + 1;
		portal: for (int i = 0; i < 3; i++) {
			int nextY = y + dy[i];
			if (space[nextY][nextX] == 1) {
				space[nextY][nextX] = 2;
				flag = putPipeLine(nextY, x + 1, space);
				if (flag == 1)
					break portal;
//				space[nextY][nextX] = 1;
			}
		}
		return flag;
	}
}
