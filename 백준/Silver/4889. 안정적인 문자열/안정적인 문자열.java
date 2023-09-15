import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;

		while (true) {
			String str = br.readLine();
//			char[] str = br.readLine().toCharArray();
//			if(str[0] == '-')
			if(str.charAt(0) == '-')
				break;
			int r = 0;
			int l = 0;
			int len = str.length();
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) == '{')
					r++;
				else {
					if (r > 0)
						r--;
					else
						l++;
				}
			}
			sb.append(tc++).append(". ");
			int ans = (r + l) / 2;
			if (r % 2 == 0)
				sb.append(ans);
			else
				sb.append(ans + 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}