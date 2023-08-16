import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] data = new char[n][n];
		for (int i = 0; i < n; i++)
			data[i] = br.readLine().toCharArray();

		quadTree(0, 0, n, data);

		System.out.println(sb);
	}

	private static void quadTree(int y, int x, int n, char[][] data) {
		if (n == 1) {
			sb.append(data[y][x]);
			return;
		}
		Character sign = checkSpace(y, x, n, data);
		if (sign != null) {
			sb.append(sign);
			return;
		}

		sb.append("(");
		quadTree(y, x, n / 2, data);
		quadTree(y, x + n / 2, n / 2, data);
		quadTree(y + n / 2, x, n / 2, data);
		quadTree(y + n / 2, x + n / 2, n / 2, data);
		sb.append(")");
	}

	private static Character checkSpace(int y, int x, int n, char[][] data) {
		char standard = data[y][x];
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++)
				if (standard != data[i][j])
					return null;
		}
		return standard;
	}
}
