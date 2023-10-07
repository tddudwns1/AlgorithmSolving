import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String t = br.readLine();
		String p = br.readLine();
		int pi[] = getPi(p);
		List<Integer> ans = new ArrayList<>();
		KMP(t, p, pi, ans);
		System.out.println(ans.size());
		for (int e : ans)
			sb.append(e).append(" ");
		System.out.println(sb);
	}

	static int[] getPi(String p) {
		int[] pi = new int[p.length()];
		int j = 0;
		for (int i = 1; i < p.length(); i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j))
				j = pi[j - 1];
			if (p.charAt(i) == p.charAt(j))
				pi[i] = ++j;
		}
		return pi;
	}

	static void KMP(String t, String p, int[] pi, List<Integer> ans) {
		int j = 0;
		int len = p.length() - 1;
		for (int i = 0; i < t.length(); i++) {
			while (j > 0 && t.charAt(i) != p.charAt(j))
				j = pi[j - 1];
			if (t.charAt(i) == p.charAt(j))
				if (j == len) {
					ans.add(i - j + 1);
					j = pi[j];
				} else
					j++;
		}
	}
}