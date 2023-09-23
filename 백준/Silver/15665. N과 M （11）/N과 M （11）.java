import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static Set<String> set = new LinkedHashSet<>();
	static Set<Integer> numset = new TreeSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			numset.add(Integer.parseInt(st.nextToken()));
		n = numset.size();
		int[] nums = numset.stream().mapToInt(Integer::intValue).toArray();
		rec(0, "", n, m, nums);

		StringBuilder sb = new StringBuilder();

		for (String e : set)
			sb.append(e).append("\n");

		System.out.println(sb);
	}

	private static void rec(int index, String ans, int n, int m, int[] nums) {
		if (index == m) {
			set.add(ans);
			return;
		}

		for (int i = 0; i < n; i++) {
			rec(index + 1, ans + nums[i] + " ", n, m, nums);
		}
	}
}