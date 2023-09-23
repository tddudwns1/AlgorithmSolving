import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<String> set = new LinkedHashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[n];
		for(int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);

		rec(0, 0, "", n, m, nums);
		
		StringBuilder sb = new StringBuilder();
		
		for(String e : set)
			sb.append(e).append("\n");
		
		System.out.println(sb);
	}

	private static void rec(int index, int start, String ans, int n, int m, int[] nums) {
		if (index == m) {
			set.add(ans);
			return;
		}

		for (int i = start; i < n; i++) {
			rec(index + 1, i + 1, ans + nums[i] + " ", n, m, nums);
		}
	}
}