import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] check = new int[n + 1];
		Deque<Integer>[] priority = new ArrayDeque[n + 1];

		for (int i = 1; i <= n; i++) {
			priority[i] = new ArrayDeque<>();
		}

		for (int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int[] ks = new int[k];
			for (int i = 0; i < k; i++) {
				ks[i] = Integer.parseInt(st.nextToken());
				for (int j = 0; j < i; j++) {
					priority[ks[i]].add(ks[j]);
				}
			}
		}
		for (int i = 1; i <= n; i++)
			if (check[i] == 0)
				dfs(priority, check, i);

		if (flag)
			System.out.println(0);
		else
			System.out.println(sb);
	}

	private static void dfs(Deque<Integer>[] priority, int[] check, int idx) {
		if (check[idx] == 2 || flag) {
			return;
		}

		if (check[idx] == 1) {
			flag = true;
			return;
		}

		if (!priority[idx].isEmpty())
			check[idx] = 1;

		while (!priority[idx].isEmpty()) {
			dfs(priority, check, priority[idx].removeFirst());
		}

		check[idx] = 2;
		sb.append(idx).append("\n");
	}
}