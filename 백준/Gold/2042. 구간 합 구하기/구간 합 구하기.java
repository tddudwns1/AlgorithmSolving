import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class SegmentTree {
		long[] tree;

		public SegmentTree(int n) {
			tree = new long[4 * n];
		}

		public long init(int node, int start, int end, long[] arr) {
			if (start == end)
				return tree[node] = arr[start];

			int mid = (start + end) / 2;
			return tree[node] = init(node * 2, start, mid, arr) + init(node * 2 + 1, mid + 1, end, arr);
		}

		public void update(int node, int start, int end, int target, long dif) {
			if (target < start || end < target)
				return;

			tree[node] += dif;

			if (start == end)
				return;

			int mid = (start + end) / 2;
			update(node * 2, start, mid, target, dif);
			update(node * 2 + 1, mid + 1, end, target, dif);
		}

		public long sum(int node, int start, int end, int left, int right) {
			if (right < start || end < left)
				return 0;

			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int mk = Integer.parseInt(st.nextToken()) + m;

		long[] arr = new long[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Long.parseLong(br.readLine());

		SegmentTree tree = new SegmentTree(n);
		tree.init(1, 1, n, arr);

		for (int i = 0; i < mk; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			if (command == 1) {
				tree.update(1, 1, n, a, b - arr[a]);
				arr[a] = b;
			} else {
				sb.append(tree.sum(1, 1, n, a, (int) b)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}