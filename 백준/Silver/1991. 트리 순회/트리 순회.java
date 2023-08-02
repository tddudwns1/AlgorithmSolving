import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static Node head = new Node("A", null, null);

	static class Node {
		String name;
		Node left;
		Node right;

		public Node(String name, Node left, Node right) {
			this.name = name;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < 3; j++)
				setTree(head, line[0], line[1], line[2]);
		}
		preorder(head);
		System.out.println();
		inorder(head);
		System.out.println();
		postorder(head);
	}

	private static void postorder(Node node) {
		if (node == null)
			return;
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.name);
	}

	private static void inorder(Node node) {
		if (node == null)
			return;
		inorder(node.left);
		System.out.print(node.name);
		inorder(node.right);
	}

	private static void preorder(Node node) {
		if (node == null)
			return;
		System.out.print(node.name);
		preorder(node.left);
		preorder(node.right);
	}

	private static void setTree(Node tmp, String parent, String left, String right) {
		if (tmp.name.equals(parent)) {
			if (left != null && !left.equals("."))
				tmp.left = new Node(left, null, null);
			if (right != null && !right.equals("."))
				tmp.right = new Node(right, null, null);
		} else {
			if (tmp.left != null)
				setTree(tmp.left, parent, left, right);
			if (tmp.right != null)
				setTree(tmp.right, parent, left, right);
		}
	}
}
