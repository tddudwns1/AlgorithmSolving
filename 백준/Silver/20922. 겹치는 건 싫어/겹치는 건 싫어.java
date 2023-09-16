import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] sequence = new int[n];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++)
			sequence[i] = Integer.parseInt(st.nextToken());

		int[] cnt = new int[100001];
		int left = -1;
		int right = -1;
		int max = 0;
		while (true) {
			while (++right < n && ++cnt[sequence[right]] <= k)
				;
			max = Math.max(max, right - left);
			if(right == n)
				break;
			while (cnt[sequence[right]] > k)
				--cnt[sequence[++left]];
		}
		System.out.println(max - 1);
	}
}