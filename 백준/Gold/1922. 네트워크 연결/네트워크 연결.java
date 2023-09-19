import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	public static void union(int a, int b, int[] parent) {
		a = find(a, parent);
		b = find(b, parent);

		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	public static int find(int n, int[] parent) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n], parent);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int[] parent = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;

		Queue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (a == b)
				continue;

			pq.add(new Edge(a, b, cost));
		}
		int cost = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			if (find(e.a, parent) == find(e.b, parent))
				continue;

			union(e.a, e.b, parent);
			cost += e.cost;
		}

		System.out.println(cost);
	}
}
