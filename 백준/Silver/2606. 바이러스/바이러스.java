import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static boolean[][] computers;
	static boolean[] checked;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		checked = new boolean[n + 1];
		computers = new boolean[n + 1][n + 1];
		for (int i = 0; i < T; i++) {
			String[] connectStr = br.readLine().split(" ");
			int a = Integer.parseInt(connectStr[0]);
			int b = Integer.parseInt(connectStr[1]);
			computers[a][b] = true;
			computers[b][a] = true;
		}
		bfs();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		checked[1] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int virus = q.poll();
			for (int i = 1; i <= n; i++) {
				if (computers[virus][i] && !checked[i]) {
					q.add(i);
					checked[i] = true;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}