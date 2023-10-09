import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Building[] info;

	static class Building {
		int delay;
		Queue<Integer> before;

		public Building(int delay) {
			this.delay = delay;
			before = new LinkedList<>();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			info = new Building[n + 1];
			for (int i = 1; i <= n; i++)
				info[i] = new Building(Integer.parseInt(st.nextToken()));

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				info[y].before.add(x);
			}

			System.out.println(dfs(Integer.parseInt(br.readLine())));
		}
	}

	private static int dfs(int now) {
		if (info[now].before.size() != 0) {
			int max = 0;
			while(!info[now].before.isEmpty()) {
				max = Math.max(dfs(info[now].before.poll()), max);
			}

			info[now].delay += max;
		}

	return info[now].delay;
}}
