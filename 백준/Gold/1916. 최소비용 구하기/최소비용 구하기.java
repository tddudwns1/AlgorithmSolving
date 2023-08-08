import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Router implements Comparable<Router> {
		int index;
		int height;

		public Router(int index, int height) {
			this.index = index;
			this.height = height;
		}

		@Override
		public int compareTo(Router o) {
			return this.height - o.height;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] costs = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				costs[i][j] = Integer.MAX_VALUE;
		int[] lowCosts = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		Arrays.fill(lowCosts, Integer.MAX_VALUE);
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (costs[y][x] > cost)
				costs[y][x] = cost;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start, costs, lowCosts, n, visited);
		System.out.println(lowCosts[end]);
	}

	private static void dijkstra(int start, int[][] costs, int[] lowCosts, int n, boolean[] visited) {
		PriorityQueue<Router> q = new PriorityQueue<>();
		lowCosts[start] = 0;
		q.add(new Router(start, lowCosts[start]));

		while (!q.isEmpty()) {
			Router now = q.poll();
			for (int i = 1; i <= n; i++) {
				if (costs[now.index][i] == Integer.MAX_VALUE)
					continue;
				if (visited[i])
					continue;
				if (lowCosts[i] > now.height + costs[now.index][i])
					q.add(new Router(i, lowCosts[i] = now.height + costs[now.index][i]));
			}
		}
	}
}