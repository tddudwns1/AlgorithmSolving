import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int min = Integer.MAX_VALUE, n;
	static int[][] costs;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		costs = new int[n][n];

		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				costs[i][j] = Integer.parseInt(st.nextToken());
		}
//		for (int i = 0; i < n; i++) {
			visited[0] = true;
			dfs(0, 0, 0, n - 1);
//			visited[i] = false;
//		}
		System.out.println(min);
	}

	private static void dfs(int startCity, int nowCity, int nowCosts, int dep) {
		if (dep == 0) {
			if (costs[nowCity][startCity] != 0)
				min = Math.min(min, nowCosts + costs[nowCity][startCity]);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && costs[nowCity][i] != 0 && nowCosts + costs[nowCity][i] < min) {
				visited[i] = true;
				dfs(startCity, i, nowCosts + costs[nowCity][i], dep - 1);
				visited[i] = false;
			}
		}
	}
}
