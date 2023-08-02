import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int n;
	static int m;
	static int[] nums;
	static int[] ans;
	static StringBuilder sb;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		ans = new int[m];
		visited = new boolean[n];

		comb(0);
		System.out.println(sb);
	}

	private static void comb(int len) {
		if(len == m) {
			for(int i = 0; i < m; i++)
				sb.append(ans[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i])
				continue;
			ans[len] = nums[i];
			visited[i] = true;
			comb(len + 1);
			visited[i] = false;
		}
		
	}
}