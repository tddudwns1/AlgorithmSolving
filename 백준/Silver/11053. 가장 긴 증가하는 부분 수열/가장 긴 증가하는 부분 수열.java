import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		int[] cnt = new int[n];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(str[i]);

		for (int i = n - 1; i >= 0; i--) {
			int maxCnt = 0;
			for (int j = i + 1; j < n; j++) {
				if (a[i] < a[j])
					maxCnt = Math.max(maxCnt, cnt[j] + 1);
				if (a[i] == a[j])
					maxCnt = Math.max(maxCnt, cnt[j]);
			}
			cnt[i] = maxCnt;
		}
		Arrays.sort(cnt);
		System.out.println(cnt[n - 1] + 1);

	}
}
