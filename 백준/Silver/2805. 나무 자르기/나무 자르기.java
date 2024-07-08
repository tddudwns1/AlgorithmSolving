import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		String[] trees = br.readLine().split(" ");
		int[] h = new int[n + 1];
		for (int i = 0; i < n; i++)
			h[i + 1] = Integer.parseInt(trees[i]);
		Arrays.sort(h);

		long sum = 0; // 마지막에 가장 높은 높이에서 뺄 카운팅
		long cnt = 1; // 한번에 벨 나무 양
		for (int i = n; 0 < i; i--) {
			long dif = h[i] - h[i - 1];
			if (dif * cnt >= m) {
				sum += (long) Math.ceil(m / (double) cnt);
				break;
			}
			m -= dif * cnt;
			sum += dif;
			cnt++;
		}
		System.out.println(h[n] - sum);
	}
}
