import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 1; i <= n; i++)
			q.add(i);
		sb.append("<");
		while (!q.isEmpty()) {
			for (int i = 0; i < k; i++)
				q.add(q.poll());
			sb.append(q.poll()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb);
	}
}
