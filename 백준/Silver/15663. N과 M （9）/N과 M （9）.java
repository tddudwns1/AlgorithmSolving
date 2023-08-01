import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class Main {
	static int n;
	static int[] seq;
	static Set<String> set;
	static Stack<Integer> stack;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		String[] seqstr = br.readLine().split(" ");
		seq = new int[n];
		for(int i = 0; i < n; i++)
			seq[i] = Integer.parseInt(seqstr[i]);
		Arrays.sort(seq);
//		System.out.println(Arrays.toString(seq));
		
		set = new LinkedHashSet<>();
		stack = new Stack<>();
		visited = new boolean[n];

		dfs(m);
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	private static void dfs(int m) {
		if (m == 0) {
			Iterator<Integer> it = stack.iterator();
			String ans = "";
			while (it.hasNext())
				ans += it.next() + " ";
			set.add(ans);
			return;
		}
		for (int i = 0; i < n; i++)
			if (!visited[i]) {
				stack.add(seq[i]);
				visited[i] = true;
				dfs(m - 1);
				stack.pop();
				visited[i] = false;
			}
	}
}
