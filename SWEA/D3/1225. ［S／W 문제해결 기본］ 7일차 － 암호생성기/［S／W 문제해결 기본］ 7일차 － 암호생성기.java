import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] password = new int[8];
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 8; i++) {
				password[i] = Integer.parseInt(st.nextToken());
				min = Math.min(min, password[i] / 15 - 1);
			}
			if (min > 0)
				for (int i = 0; i < 8; i++)
					password[i] %= 15 * min;
			int sub = 1;
			int i = 0;
			while (true) {
				password[i] -= sub;
				if (password[i] <= 0) {
					password[i] = 0;
					for (int j = 0; j < 8; j++)
						sb.append(password[++i % 8]).append(" ");
					break;
				}
				i = (i + 1) % 8;
				sub = sub % 5 + 1;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
