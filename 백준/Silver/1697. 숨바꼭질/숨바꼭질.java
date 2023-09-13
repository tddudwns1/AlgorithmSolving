import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] visited = new int[199_999];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if (n == k) {
			System.out.println(0);
			return;
		}
		visited[n] = 1;

		System.out.println(bfs(n, k) - 1);
	}

	private static int bfs(int n, int k) {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);

		while (!q.isEmpty()) {
			int now = q.poll();
			if (now == k)
				return visited[now];
			if (now < k) {
				if (visited[now * 2] == 0) {
					q.add(now * 2);
					visited[now * 2] = visited[now] + 1;
				}
				if (visited[now + 1] == 0) {
					q.add(now + 1);
					visited[now + 1] = visited[now] + 1;
				}
			}
			if (now - 1 >= 0 && visited[now - 1] == 0) {
				q.add(now - 1);
				visited[now - 1] = visited[now] + 1;
			}
		}
		return -1;
	}
}
