import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] points = new long[n + 1][2];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			points[i] = new long[] { a, b };
		}
		points[n][0] = points[0][0];
		points[n][1] = points[0][1];
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += points[i - 1][0] * points[i][1] - points[i - 1][1] * points[i][0];
		}

		System.out.printf("%.1f", (double) Math.abs(sum) / 2);
	}
}
