import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		String[] sequence = br.readLine().split(" ");

		int[][] palindrome = new int[n][2];
		for (int i = 0; i < n; i++) {
			palindrome[i][0] = palindrome(i, i, sequence, n);
			palindrome[i][1] = palindrome(i, i + 1, sequence, n);
		}

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int sum = Integer.parseInt(st.nextToken()) - 1 + left;
			int half = sum / 2;
			int target = 0;

			if (sum % 2 == 0)
				target = palindrome[half][0];
			else
				target = palindrome[half][1];

			if (target > half - left)
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		
		System.out.println(sb);
	}

	private static int palindrome(int left, int right, String[] sequence, int n) {
		int cnt = 0;
		while (left >= 0 && right < n) {
			if (!sequence[left--].equals(sequence[right++]))
				break;
			cnt++;
		}
		return cnt;
	}
}
