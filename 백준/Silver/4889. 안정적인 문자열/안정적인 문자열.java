import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;

		while (true) {
			char[] str = br.readLine().toCharArray();
			if(str[0] == '-')
				break;
			int r = 0;
			int l = 0;
			for (int i = 0; i < str.length; i++) {
				if (str[i] == '{')
					r++;
				else {
					if (r > 0)
						r--;
					else
						l++;
				}
			}
			sb.append(tc++).append(". ");
			if (r % 2 == 0)
				sb.append((r + l) / 2);
			else
				sb.append((r + l) / 2 + 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}