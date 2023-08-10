import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	static String[][] table;
	static int r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		table = new String[n][m];
		for (int i = 0; i < n; i++)
			table[i] = br.readLine().split(" ");

		spinTable(0, n - 1, m - 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				sb.append(table[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void spinTable(int start, int row, int col) {
		while (true) {
			if (row <= start || col <= start)
				return;
			Deque<String> q = new LinkedList<>();
			for (int i = start; i < row; i++)
				q.addLast(table[i][start]);
			for (int i = start; i < col; i++)
				q.addLast(table[row][i]);
			for (int i = row; i > start; i--)
				q.addLast(table[i][col]);
			for (int i = col; i > start; i--)
				q.addLast(table[start][i]);
			for (int i = r % ((row + col- start * 2) * 2); i > 0; i--)
				q.addFirst(q.removeLast());
			for (int i = start; i < row; i++)
				table[i][start] = q.removeFirst();
			for (int i = start; i < col; i++)
				table[row][i] = q.removeFirst();
			for (int i = row; i > start; i--)
				table[i][col] = q.removeFirst();
			for (int i = col; i > start; i--)
				table[start][i] = q.removeFirst();
			row--;
			col--;
			start++;
		}
	}
}
