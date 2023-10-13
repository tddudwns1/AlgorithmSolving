import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class TrieNode {
		TrieNode[] children;
		boolean isEndOfWord;

		public TrieNode() {
			children = new TrieNode[26];
			isEndOfWord = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		TrieNode colors = new TrieNode();
		for (int i = 0; i < c; i++)
			insert(colors, br.readLine());

		Set<String> teams = new HashSet<>();
		for (int i = 0; i < n; i++)
			teams.add(br.readLine());

		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++)
			sb.append(legend(br.readLine(), colors, teams)).append("\n");

		System.out.println(sb);
	}

	public static void insert(TrieNode node, String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int index = c - 'a';
			if (node.children[index] == null)
				node.children[index] = new TrieNode();
			node = node.children[index];
		}
		node.isEndOfWord = true;
	}

	private static String legend(String str, TrieNode colors, Set<String> teams) {
		for (int i = 0; i < str.length(); i++) {
			if (colors.isEndOfWord)
				if (teams.contains(str.substring(i)))
					return "Yes";
			char c = str.charAt(i);
			int index = c - 'a';
			if (colors.children[index] == null)
				break;
			colors = colors.children[index];
		}
		return "No";
	}
}