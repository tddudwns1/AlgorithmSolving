import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int n;
	static int m;
	static int[] ans;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = new int[m];

		comb(1, 0);
		System.out.println(sb);
	}

	private static void comb(int point, int len) {
		if (len == m) {
			for(int i = 0; i < m; i++)
				sb.append(ans[i]).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = point; i <= n; i++) {
			ans[len] = i;
			comb(i, len + 1);
		}
	}
}