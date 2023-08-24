import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int cnt = 1, ans = 0, max = Integer.MIN_VALUE;
		int[] chk = new int[d + 1];
		int[] belt = new int[n];
	    chk[c]++;// 쿠폰 초밥 미리 추가

		for (int i = 0; i < n; i++) {
			belt[i] = Integer.parseInt(br.readLine());// 값 받아오기
		}

		for (int j = 0; j < k; j++)
			if (chk[belt[j]]++ == 0)
				cnt++;

		for (int i = 1; i < n; i++) {
			int last = belt[i - 1];
			if (chk[last]-- == 1)
				cnt--;
			int next_idx = i + k - 1;
			if (next_idx >= n)
				next_idx -= n;
			if (chk[belt[next_idx]]++ == 0)
				cnt++;

			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}
}