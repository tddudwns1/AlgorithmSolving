import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(a, b));
	}
	
	public static class Node{
		long a;
		long cnt;
		public Node(long a, long cnt) {
			super();
			this.a = a;
			this.cnt = cnt;
		}
	}

	private static long bfs(long a, long b) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, 1));
		while(!q.isEmpty()) {
			Node c = q.poll();
			if(c.a * 10 + 1 == b || c.a * 2 == b)
				return c.cnt + 1;
			if(c.a * 10 + 1 < b)
				q.add(new Node(c.a * 10 + 1, c.cnt + 1));
			if(c.a * 2 < b)
				q.add(new Node(c.a * 2, c.cnt + 1));
		}
		return -1;
	}
}
