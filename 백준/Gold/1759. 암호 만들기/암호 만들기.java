import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int l, c;
	static char[] candidate, ans;
	static StringBuilder sb = new StringBuilder();
	static char[] aeiou = new char[] { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		ans = new char[l];
		candidate = new char[c];
		candidate = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(candidate);
		int index = 0;
		int conCnt = 2;
		int vowCnt = 1;
		int len = 0;
		dfs(index, conCnt, vowCnt, len);
		System.out.println(sb);
	}

	private static void dfs(int index, int conCnt, int vowCnt, int len) {
		if (l - len < conCnt || l - len < vowCnt)
			return;

		if (len == l) {
			for (int i = 0; i < l; i++)
				sb.append(ans[i]);
			sb.append("\n");
			return;
		}

		for (int i = index; i < c; i++) {
			ans[len] = candidate[i];
			if (aeiou(candidate[i]))
				dfs(i + 1, conCnt, vowCnt - 1, len + 1);
			else
				dfs(i + 1, conCnt - 1, vowCnt, len + 1);
		}
	}

	private static boolean aeiou(char ch) {
		for (int i = 0; i < 5; i++)
			if (aeiou[i] == ch)
				return true;
		return false;
	}
}
