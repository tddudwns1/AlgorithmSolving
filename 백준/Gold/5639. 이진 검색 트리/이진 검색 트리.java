import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static class Router {
		int num;
		Router left;
		Router right;

		public Router(int num) {
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Router root = new Router(Integer.parseInt(br.readLine()));
		while (true) {
			String input = br.readLine();
			if (input == null || input.equals(""))
				break;
			int n = Integer.parseInt(input);
			Queue<Router> q = new LinkedList<>();
			q.add(root);
			while (!q.isEmpty()) {
				Router now = q.poll();
				if (now.num < n)
					if (now.right == null)
						now.right = new Router(n);
					else
						q.add(now.right);
				else if (now.num > n)
					if (now.left == null)
						now.left = new Router(n);
					else
						q.add(now.left);
			}
		}

		postorder(root);
	}

	private static void postorder(Router tree) {
		if (tree == null)
			return;
		postorder(tree.left);
		postorder(tree.right);
		System.out.println(tree.num);
	}
}
