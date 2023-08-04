import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static int n;
	static int[] dist;
	static List<List<Router>> list;

	static class Router implements Comparable<Router> {
		int num;
		int weight;

		public Router(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Router o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			list.add(new ArrayList<>());

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.get(a).add(new Router(b, 1));
		}

		dijkstra(x);

		for (int i = 1; i < dist.length; i++)
			if (dist[i] == k)
				sb.append(i).append('\n');

		if (sb.length() == 0)
			System.out.println(-1);
		else
			System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Router> q = new PriorityQueue<>();
		boolean visited[] = new boolean[n + 1];
		dist[start] = 0;

		q.add(new Router(start, 0));

		while (!q.isEmpty()) {
			Router router = q.poll();
			int n = router.num;
			if (visited[n])
				continue;
			visited[n] = true;
			for (Router r : list.get(n)) {
				if (!visited[r.num] && dist[r.num] > r.weight + dist[n]) {
					dist[r.num] = r.weight + dist[n];
					q.add(new Router(r.num, dist[r.num]));
				}
			}
		}
	}
}