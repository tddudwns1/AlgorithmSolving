import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	static class TrieNode {
		private TrieNode[] children;
		private boolean isEndOfWord;

		public TrieNode() {
			children = new TrieNode[27]; // 영문 알파벳 개수에 따라 조정
			isEndOfWord = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		TrieNode root = new TrieNode();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			insert(root, st);
		}
		print("", 0, root);
		System.out.println(sb); // true
	}

	public static void insert(TrieNode root, StringTokenizer st) {
		TrieNode node = root;
		int k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < k; i++) {
			String str = st.nextToken();
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				int index = c - 'A'; // 영문 알파벳에 맞게 인덱스 계산
				if (node.children[index] == null) {
					node.children[index] = new TrieNode();
				}
				node = node.children[index];
			}
			node.isEndOfWord = true;
			if (node.children[26] == null)
				node.children[26] = new TrieNode();
			node = node.children[26];
		}
	}

	public static void print(String str, int times, TrieNode node) {
		if (node.children[26] != null) {
			for (int i = 0; i < times; i++)
				sb.append("--");
			sb.append(str).append("\n");
			print("", times + 1, node.children[26]);
		}
		for (int i = 0; i < 26; i++) {
			if (node.children[i] == null)
				continue;
			print(str + (char) ('A' + i), times, node.children[i]);
		}
	}
}
