import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int s = Integer.parseInt(str[0]);
		int p = Integer.parseInt(str[1]);
		char[] dna = br.readLine().toCharArray();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] ans = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		int[] cnt = new int[4];
		int sum = 0;
		for (int i = 0; i < p; i++)
			cnt[getDNAIdx(dna[i])]++;
		for (int j = 0; j < 4; j++) {
			if (cnt[j] < ans[j])
				break;
			if (j == 3) 
				sum++;
		}
		for (int i = p; i < s; i++) {
			cnt[getDNAIdx(dna[i - p])]--;
			cnt[getDNAIdx(dna[i])]++;
			for (int j = 0; j < 4; j++) {
				if (cnt[j] < ans[j])
					break;
				if (j == 3) 
					sum++;
			}
		}
		System.out.println(sum);
	}

	private static int getDNAIdx(char ch) {
		if (ch == 'A')
			return 0;
		else if (ch == 'C')
			return 1;
		else if (ch == 'G')
			return 2;
		else
			return 3;
	}
}