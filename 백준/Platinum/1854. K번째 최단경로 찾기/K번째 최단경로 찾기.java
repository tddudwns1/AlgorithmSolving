import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int node;
		int cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer>[] dis = new PriorityQueue[n + 1];
		Queue<Edge>[] len = new ArrayDeque[n + 1];
		for (int i = 1; i <= n; i++) {
			dis[i] = new PriorityQueue<>(Collections.reverseOrder());
			len[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			len[Integer.parseInt(st.nextToken())]
					.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		dijkstra(dis, len, k);
		for (int i = 1; i <= n; i++) {
			if (dis[i].size() < k)
				sb.append("-1\n");
			else
				sb.append(dis[i].peek()).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(Queue<Integer>[] dis, Queue<Edge>[] len, int k) {
		Queue<Edge> q = new ArrayDeque<>();
		q.add(new Edge(1, 0));
		dis[1].add(0);

		while (!q.isEmpty()) {
			Edge now = q.poll();
			for (Edge next : len[now.node]) {
				int newCost = now.cost + next.cost;

				if (dis[next.node].size() >= k) {
					if (newCost >= dis[next.node].peek())
						continue;
					dis[next.node].poll();
				}

				dis[next.node].add(newCost);
				q.add(new Edge(next.node, newCost));
			}
		}
	}
}
