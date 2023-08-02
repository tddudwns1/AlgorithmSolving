import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	static int size;
	static int m;
	static int[] nums;
	static int[] ans;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Set<Integer> set = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			set.add(Integer.parseInt(st.nextToken()));
		size = set.size();
		nums = new int[size];
		int i = 0;
		for(int q : set)
			nums[i++] = q;
		ans = new int[m];

		comb(0, 0);
		System.out.println(sb);
	}

	private static void comb(int point, int len) {
		if (len == m) {
			for (int i = 0; i < m; i++)
				sb.append(ans[i]).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = point; i < size; i++) {
			ans[len] = nums[i];
			comb(i, len + 1);
		}

	}
}