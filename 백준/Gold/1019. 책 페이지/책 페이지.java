import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		int[] cnts = new int[10];
		int start = 1, end = n, digit = 1;
		while (start <= end) {
			while (end % 10 != 9 && start <= end)
				calc(end--, cnts, digit);

			if (end < start)
				break;

			while (start % 10 != 0 && start <= end)
				calc(start++, cnts, digit);

			start /= 10;
			end /= 10;

			for (int i = 0; i < 10; i++)
				cnts[i] += (end - start + 1) * digit;

			digit *= 10;
		}

		for (int cnt : cnts)
			sb.append(cnt).append(" ");
		System.out.println(sb);
	}

	private static void calc(int t, int[] cnts, int digit) {
		while (t > 0) {
			cnts[t % 10] += digit;
			t /= 10;
		}
	}
}