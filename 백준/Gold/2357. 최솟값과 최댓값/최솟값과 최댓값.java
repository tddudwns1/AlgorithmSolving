import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class SegmentTree {
		int[][] tree;

		public SegmentTree(int n) {
			tree = new int[4 * n][2];
		}

		public int[] init(int node, int start, int end, int[] arr) {
			if (start == end) {
				tree[node][0] = arr[start];
				tree[node][1] = arr[start];
				return tree[node];
			}

			int mid = (start + end) / 2;
			int[] leftc = init(node * 2, start, mid, arr);
			int[] rightc = init(node * 2 + 1, mid + 1, end, arr);
			tree[node][0] = Math.min(leftc[0], rightc[0]);
			tree[node][1] = Math.max(leftc[1], rightc[1]);

			return tree[node];
		}

		public int[] find(int node, int start, int end, int left, int right) {
			if (right < start || end < left)
				return new int[] { Integer.MAX_VALUE, 0 };

			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;
			int[] leftc = find(node * 2, start, mid, left, right);
			int[] rightc = find(node * 2 + 1, mid + 1, end, left, right);
			int[] ans = new int[2];
			ans[0] = Math.min(leftc[0], rightc[0]);
			ans[1] = Math.max(leftc[1], rightc[1]);

			return ans;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		SegmentTree tree = new SegmentTree(n);
		tree.init(1, 1, n, arr);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int[] ans = tree.find(1, 1, n, left, right);
			sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
		}
		System.out.println(sb);
	}
}