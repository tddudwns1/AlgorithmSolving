import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int connect;
		int weight;

		public Edge(int connect, int weight) {
			this.connect = connect;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Edge>[] houses = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++)
			houses[i] = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			houses[a].add(new Edge(b, weight));
			houses[b].add(new Edge(a, weight));
		}

		System.out.println(prim(houses, n, m));
	}

	private static int prim(List<Edge>[] houses, int n, int m) {
		int ans = 0;
		int max = 0;
		Queue<Edge> q = new PriorityQueue<>();
		boolean[] visited = new boolean[n + 1];

		q.add(new Edge(1, 0));

		while (!q.isEmpty()) {
			Edge now = q.poll();

			if (visited[now.connect])
				continue;
			visited[now.connect] = true;
			ans += now.weight;
			max = Math.max(max, now.weight);

			for (Edge next : houses[now.connect]) {
				if (visited[next.connect])
					continue;
				q.add(next);
			}
		}

		return ans - max;
	}
}
