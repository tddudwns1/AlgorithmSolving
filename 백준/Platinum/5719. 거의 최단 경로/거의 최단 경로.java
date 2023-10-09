import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int node;
		int cost;

		public Edge(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		while (n != 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			Queue<Edge>[] dis = new ArrayDeque[n];
			Queue<Integer>[] root = new ArrayDeque[n];
			int[] dp = new int[n];
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				dis[i] = new ArrayDeque<>();
				root[i] = new ArrayDeque<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				dis[Integer.parseInt(st.nextToken())]
						.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			dijkstra(dp, dis, root, visited, s, d);
			exceptRoot(s, d, root, visited);
			dijkstra(dp, dis, root, visited, s, d);

			if (dp[d] == Integer.MAX_VALUE)
				sb.append(-1);
			else
				sb.append(dp[d]);
			sb.append("\n");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}

	private static void dijkstra(int[] dp, Queue<Edge>[] dis, Queue<Integer>[] root, boolean[][] visited, int s,
			int d) {
		Queue<Edge> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[s] = 0;
		q.add(new Edge(s, 0));

		while (!q.isEmpty()) {
			Edge now = q.poll();

			if (now.cost > dp[now.node])
				continue;

			for (Edge next : dis[now.node]) {
				if (visited[now.node][next.node])
					continue;
				int newCost = dp[now.node] + next.cost;
				if (dp[next.node] < newCost)
					continue;
				if (dp[next.node] > newCost) {
					dp[next.node] = newCost;
					root[next.node] = new ArrayDeque<>();
					q.add(new Edge(next.node, dp[next.node]));
				}
				root[next.node].add(now.node);
				if (next.node == d)
					continue;
			}

		}
	}

	private static void exceptRoot(int s, int d, Queue<Integer>[] root, boolean[][] visited) {
		if (s == d)
			return;
		for (int next : root[d]) {
			if (!visited[next][d]) {
				visited[next][d] = true;
				exceptRoot(s, next, root, visited);
			}
		}
	}
}
