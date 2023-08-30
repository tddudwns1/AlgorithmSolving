import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String first = br.readLine();
		char[] words = first.toCharArray();
		Queue<Character> ab = new LinkedList<>();
		Queue<Character> a = new LinkedList<>();
		Stack<Character> b = new Stack<>();
		int len = words.length;
		for (int i = 0; i < len; i++)
			ab.add(words[i]);

		for (int i = 0; i < n; i++) {
			int idx = -1;
			while (!ab.isEmpty())
				if (++idx % 2 == 0)
					a.add(ab.poll());
				else
					b.add(ab.poll());

			while (!a.isEmpty())
				ab.add(a.poll());
			while (!b.isEmpty())
				ab.add(b.pop());
			for (char ch : ab)
				sb.append(ch);
			if (first.equals(sb.toString()))
				n = (i + 1) + n % (i + 1);

			sb.setLength(0);
		}
		for (char ch : ab)
			sb.append(ch);
		System.out.println(sb);
	}
}
