import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] sums = new int[n + 1];
		String[] numstr = br.readLine().split(" ");
		sums[0] = Integer.parseInt(numstr[0]);
		for (int i = 0; i < n; i++)
			sums[i + 1] += sums[i] + Integer.parseInt(numstr[i]);

		for (int tc = 1; tc <= m; tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(sums[b] - sums[a - 1]).append("\n");
		}
		System.out.println(sb);
	}
}
