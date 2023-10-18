import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int idx;
		int val;

		public Node(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

	static class SegmentTree {
		Node[] tree;

		public SegmentTree(int n) {
			tree = new Node[4 * n];
		}

		public Node init(int node, int start, int end, int[] arr) {
			if (start == end)
				return tree[node] = new Node(end, arr[end]);

			int mid = (start + end) / 2;
			Node first = init(node * 2, start, mid, arr);
			Node second = init(node * 2 + 1, mid + 1, end, arr);
			if (first.val > second.val)
				return tree[node] = second;
			else
				return tree[node] = first;
		}

		public Node update(int node, int start, int end, int target, int val) {
			if (target < start || end < target)
				return tree[node];

			if (start == end)
				if (start == target) {
					tree[node].val = val;
					return tree[node];
				} else
					return tree[node];

			int mid = (start + end) / 2;
			Node first = update(node * 2, start, mid, target, val);
			Node second = update(node * 2 + 1, mid + 1, end, target, val);
			if (first.val > second.val)
				return tree[node] = second;
			else
				return tree[node] = first;
		}

		public Node getMin(int node, int start, int end, int left, int right) {
			if (right < start || end < left)
				return new Node(0, Integer.MAX_VALUE);

			if (left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;
			Node first = getMin(node * 2, start, mid, left, right);
			Node second = getMin(node * 2 + 1, mid + 1, end, left, right);
			if (first.val > second.val)
				return second;
			else
				return first;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		SegmentTree tree = new SegmentTree(n);
		tree.init(1, 1, n, arr);

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String com = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (com.equals("1")) {
				tree.update(1, 1, n, a, b);
				arr[a] = b;
			} else {
				sb.append(tree.getMin(1, 1, n, a, b).idx).append("\n");
			}
		}
		System.out.println(sb);
	}
}