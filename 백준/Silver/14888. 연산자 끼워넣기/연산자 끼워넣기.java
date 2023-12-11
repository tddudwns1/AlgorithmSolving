import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int add = Integer.parseInt(st.nextToken()), sub = Integer.parseInt(st.nextToken()),
				mul = Integer.parseInt(st.nextToken()), div = Integer.parseInt(st.nextToken());

		calc(add, sub, mul, div, 1, nums[0]);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void calc(int add, int sub, int mul, int div, int index, int now) {
		if (index == n) {
			max = Integer.max(max, now);
			min = Integer.min(min, now);
			return;
		}

		if (add > 0)
			calc(add - 1, sub, mul, div, index + 1, now + nums[index]);
		if (sub > 0)
			calc(add, sub - 1, mul, div, index + 1, now - nums[index]);
		if (mul > 0)
			calc(add, sub, mul - 1, div, index + 1, now * nums[index]);
		if (div > 0)
			calc(add, sub, mul, div - 1, index + 1, now / nums[index]);
	}
}