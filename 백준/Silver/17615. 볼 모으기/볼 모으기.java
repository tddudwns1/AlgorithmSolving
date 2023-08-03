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
		char[] chs = br.readLine().toCharArray();

		int redCnt = 0;
		int blueCnt = 0;

		for (int i = 0; i < n; i++)
			if (chs[i] == 'R')
				redCnt++;
		blueCnt = n - redCnt;

		int leftColorCnt = 0;
		int rightColorCnt = 0;

		for (int i = 1; i < n; i++) {
			leftColorCnt++;
			if (chs[0] != chs[i])
				break;
		}
		for (int i = n - 2; i >= 0; i--) {
			rightColorCnt++;
			if (chs[n - 1] != chs[i])
				break;
		}
		int[] ans = new int[4];
		if (chs[0] == 'R') {
			ans[0] = redCnt - leftColorCnt;
			ans[1] = blueCnt;
		} else {
			ans[0] = redCnt;
			ans[1] = blueCnt - leftColorCnt;
		}
		if (chs[n - 1] == 'R') {
			ans[2] = redCnt - rightColorCnt;
			ans[3] = blueCnt;
		} else {
			ans[2] = redCnt;
			ans[3] = blueCnt - rightColorCnt;
		}
		Arrays.sort(ans);
		System.out.println(ans[0]);
	}
}