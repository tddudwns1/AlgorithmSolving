import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 종류
		int k = Integer.parseInt(st.nextToken()); // 연속
		int c = Integer.parseInt(st.nextToken()); // 쿠폰

		int cnt = 1;
		int[] counts = new int[d + 1];
		int[] set = new int[k];
		int[] setFirst = new int[k];

		counts[c] = 1;
		int i = 0;
		for (; i < k; i++) {
			int input = Integer.parseInt(br.readLine());
			if (counts[input]++ == 0)
				cnt++;
			set[i] = setFirst[i] = input;
		}
		int max = cnt;
		for (; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if (--counts[set[i % k]] == 0)
				cnt--;
			set[i % k] = input;
			if (counts[input]++ == 0)
				max = Math.max(max, ++cnt);
		}
		for (int j = 0; j < k - 1; j++) {
			int input = setFirst[j];
			if (--counts[set[i % k]] == 0)
				cnt--;
			set[i++ % k] = input;
			if (counts[input]++ == 0)
				max = Math.max(max, ++cnt);
		}

		System.out.println(max);
	}
}
