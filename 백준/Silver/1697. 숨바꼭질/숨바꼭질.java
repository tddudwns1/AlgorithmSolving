import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited = new boolean[1_000_000];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int cnt = 0;
		visited[n] = true;
//		if (n == 0) {
//			n++;
//			cnt++;
//			visited[n] = true;
//		}

		System.out.println(bfs(n, m, cnt));
	}

	private static int bfs(int n, int m, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { n, cnt });

		while (!q.isEmpty()) {
			int[] now = q.poll();
			visited[now[0]] = true;
			if (now[0] == m)
				return now[1];
			cnt++;
			if (now[0] - 1 >= 0 && !visited[now[0] - 1])
				q.add(new int[] { now[0] - 1, now[1] + 1 });
			if (now[0] < m) {
				if (!visited[now[0] + 1])
					q.add(new int[] { now[0] + 1, now[1] + 1 });
				if (!visited[now[0] * 2])
					q.add(new int[] { now[0] * 2, now[1] + 1 });
			}
		}
		return -1;
	}
}
