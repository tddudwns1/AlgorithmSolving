import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] list = new int[n];
		boolean[] visited = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			list[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(list);

		Set<Integer> set = new HashSet<>();
		int ans = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++) {
				ans += search(i, j, n, list, visited);
			}

		System.out.println(ans);
	}

	private static int search(int i, int j, int n, int[] list, boolean[] visited) {
		int cnt = 0;
		int target = list[i] + list[j];
		int left = 0, right = n - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (list[mid] < target)
				left = mid + 1;
			else if (list[mid] > target)
				right = mid - 1;
			else {
				int up = mid;
				if (!visited[up] && up != i && up != j) {
					visited[up] = true;
					cnt++;
				}
				while (true) {
					if (++up == n)
						break;
					if (list[up] != target)
						break;
					if (visited[up])
						continue;
					if(up == i || up == j)
						continue;
					visited[up] = true;
					cnt++;
				}
				while (true) {
					if (--mid == -1)
						break;
					if (list[mid] != target)
						break;
					if (visited[mid])
						continue;
					if(mid == i || mid == j)
						continue;
					visited[mid] = true;
					cnt++;
				}
				break;
			}
		}
		return cnt;
	}
}