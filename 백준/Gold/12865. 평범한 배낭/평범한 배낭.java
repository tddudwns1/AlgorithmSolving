import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] knapsack = new int[k + 1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			for (int j = k; j > 0; j--) {
				if (j < w)
					break;
				if (knapsack[j] < knapsack[j - w] + v)
					knapsack[j] = knapsack[j - w] + v;
			}
		}
		System.out.println(knapsack[k]);
	}
}
