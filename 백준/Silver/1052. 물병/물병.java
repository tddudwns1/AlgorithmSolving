import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int ans = 0, idx = -1;
		String bit = Integer.toBinaryString(n);
		int cnt1 = Integer.bitCount(n);
		if (cnt1 > k) {
			for (int i = 0; i < bit.length(); i++) {
				if (k == 0) {
					idx = i;
					break;
				}
				if (bit.charAt(i) == '1')
					k--;
			}
			String temp = bit.substring(idx);
			int t = Integer.parseInt(temp, 2);
			ans = (int) (Math.pow(2, bit.length() - idx) - t);
		}
		System.out.println(ans);
	}
}
