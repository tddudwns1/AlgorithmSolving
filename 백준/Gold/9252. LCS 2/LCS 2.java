import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		int lenB = b.length;
		int lenA = a.length;
		int i = 0, j = 0;
		int[][] lcs = new int[lenB + 1][lenA + 1];
		for (i = 1; i <= lenB; i++)
			for (j = 1; j <= lenA; j++)
				if (a[j - 1] == b[i - 1])
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				else
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
		sb.append(lcs[lenB][lenA]).append("\n");
		i -= 1;
		j -= 1;
		Stack<Character> st = new Stack<>();
		while (i > 0 && j > 0) {
			if (lcs[i][j] == lcs[i - 1][j])
				i--;
			else if (lcs[i][j] == lcs[i][j - 1])
				j--;
			else {
				st.push(b[i - 1]);
				i--;
				j--;
			}
		}
		while (!st.isEmpty()) {
			sb.append(st.pop());
		}

		System.out.println(sb);
	}
}
