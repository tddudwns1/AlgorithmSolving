import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> q = new Stack<>();
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		for (int i = n; i > 0; i--)
			q.add(i);
		sb.append("<");
		int index = q.size();
		while (!q.isEmpty()) {
			index -= k;
			while(index < 0)
				index += q.size();
			sb.append(q.remove(index)).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb);
	}
}
