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
			return tree[node] = Math.min(init(node * 2, start, mid, arr), init(node * 2 + 1, mid + 1, end, arr));
		}

		public long update(int node, int start, int end, int target, long val) {
			if (target < start || end < target)
				return tree[node];

			if (start == end) {
				if (start == target) {
					tree[node] = val;
					return tree[node];
				} else
					return tree[node];
			}

			int mid = (start + end) / 2;
			return tree[node] = Math.min(update(node * 2, start, mid, target, val),
					update(node * 2 + 1, mid + 1, end, target, val));
		}

		public long getMin(int node, int start, int end, int left, int right) {
			if (right < start || end < left)
				return Long.MAX_VALUE;

			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;
			return Math.min(getMin(node * 2, start, mid, left, right), getMin(node * 2 + 1, mid + 1, end, left, right));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] arr = new long[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Long.parseLong(st.nextToken());

		SegmentTree tree = new SegmentTree(n);
		tree.init(1, 1, n, arr);

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			if (command == 1) {
				tree.update(1, 1, n, a, b);
				arr[a] = b;
			} else {
				sb.append(tree.getMin(1, 1, n, a, (int) b)).append("\n");
			}
		}

		System.out.println(sb);
	}
}