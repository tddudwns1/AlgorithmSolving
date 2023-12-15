import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer>[] q = new ArrayDeque[k + 1];
		Queue<Integer> order = new ArrayDeque<>();
		for (int i = 1; i <= k; i++)
			q[i] = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= k; i++) {
			int input = Integer.parseInt(st.nextToken());
			q[input].add(i);
			order.add(input);
		}

		for (int i = 1; i <= k; i++)
			q[i].add(Integer.MAX_VALUE);

		int[] powerbar = new int[n];
		int idx = 0, m = 0, now;

		while (idx < n) {
			if (order.isEmpty())
				break;

			q[(now = order.poll())].poll();
			m++;

			if (check(powerbar, now, idx))
				continue;

			powerbar[idx++] = now;
		}

		int cnt = 0;

		for (int i = m; i < k; i++) {
			q[(now = order.poll())].poll();
			cnt += find(now, n, q, powerbar);
		}

		System.out.println(cnt);
	}

	private static boolean check(int[] powerbar, int now, int i) {
		for (int j = 0; j < i; j++)
			if (powerbar[j] == now)
				return true;
		return false;
	}

	private static int find(int now, int n, Queue<Integer>[] q, int[] powerbar) {
		int max = 0, target = 0;
		for (int i = 0; i < n; i++)
			if (powerbar[i] == now)
				return 0;
			else if (max < q[powerbar[i]].peek())
				max = q[powerbar[(target = i)]].peek();
		powerbar[target] = now;
		return 1;
	}
}