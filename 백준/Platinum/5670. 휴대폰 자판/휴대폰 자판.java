import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static class TrieNode {
		TrieNode[] children;
		int childCnt;
		boolean isBridgeOfWord;
		boolean isEndOfWord;

		public TrieNode() {
			children = new TrieNode[26];
			childCnt = 0;
			isBridgeOfWord = false;
			isEndOfWord = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		String line;
		while((line = br.readLine())!=null) {
			try {
				int n = Integer.parseInt(line);
				TrieNode root = new TrieNode();
				String[] input = new String[n];
				for (int i = 0; i < n; i++) {
					input[i] = br.readLine();
					insert(root, input[i]);
				}
				double cnt = 0;
				for (int i = 0; i < n; i++)
					cnt += count(root, input[i]);
				sb.append(String.format("%.2f", cnt / n)).append("\n");
			} catch (Exception e) {
				break;
			}
		}
		System.out.println(sb);
	}

	public static void insert(TrieNode node, String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int index = c - 'a';
			if (node.children[index] == null) {
				node.children[index] = new TrieNode();
				if (++node.childCnt > 1)
					node.children[index].isBridgeOfWord = true;
			}
			node = node.children[index];
		}
		node.isEndOfWord = true;
	}

	private static int count(TrieNode node, String str) {
		int cnt = 1;
		node = node.children[str.charAt(0) - 'a'];
		for (int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			int index = c - 'a';
			if (node.childCnt > 1)
				cnt++;
			else if (node.isEndOfWord && i != str.length())
				cnt++;
			node = node.children[index];
		}
		return cnt;
	}
}