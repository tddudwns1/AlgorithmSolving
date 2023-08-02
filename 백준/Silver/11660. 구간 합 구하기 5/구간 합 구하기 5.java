import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] table = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 1; j <= n; j++)
				table[i][j] = Integer.parseInt(line[j - 1]) - table[i - 1][j - 1] + table[i][j - 1] + table[i - 1][j];
		}

		for (int i = 0; i < m; i++) {
			String[] xy = br.readLine().split(" ");
			int y1 = Integer.parseInt(xy[0]);
			int x1 = Integer.parseInt(xy[1]);
			int y2 = Integer.parseInt(xy[2]);
			int x2 = Integer.parseInt(xy[3]);
			sb.append(table[y2][x2] + table[y1 - 1][x1 - 1] - table[y2][x1 - 1] - table[y1 - 1][x2]).append("\n");
		}
		System.out.println(sb);
	}
}
