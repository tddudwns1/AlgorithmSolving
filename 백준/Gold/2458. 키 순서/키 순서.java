import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] compare = new int[n + 1];
		List<Integer>[] relationship = new List[n + 1];
		for (int i = 1; i <= n; i++)
			relationship[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			relationship[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= n; i++) {
			boolean[] visited = new boolean[n + 1];
			visited[i] = true;
			dfs(i, i, compare, relationship, visited);
		}

		int cnt = 0;
		for (int i = 1; i <= n; i++)
			if (compare[i] == n)
				cnt++;
		System.out.println(cnt);
	}

	private static void dfs(int start, int now, int[] compare, List<Integer>[] relationship, boolean[] visited) {
		compare[start]++;
		for (int next : relationship[now]) {
			if (visited[next])
				continue;
			visited[next] = true;
			compare[next]++;
			dfs(start, next, compare, relationship, visited);
		}
	}
}
