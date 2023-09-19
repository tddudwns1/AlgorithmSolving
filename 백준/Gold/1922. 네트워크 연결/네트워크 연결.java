import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		Deque<Node>[] dq = new ArrayDeque[n + 1];

		for (int i = 1; i <= n; i++)
			dq[i] = new ArrayDeque<>();
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (from == to)
				continue;

			dq[from].add(new Node(to, cost));
			dq[to].add(new Node(from, cost));
		}

		System.out.println(prim(n, dq));
	}

	private static int prim(int n, Deque<Node>[] dq) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n + 1];
		int cost = 0;

		while (!dq[1].isEmpty())
			pq.add(dq[1].poll());
		visited[1] = true;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (visited[now.to])
				continue;
			visited[now.to] = true;
			cost += now.cost;
			while (!dq[now.to].isEmpty())
				pq.add(dq[now.to].poll());
		}

		return cost;
	}
}
