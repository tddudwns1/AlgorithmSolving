import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Main {
	static Map<String, Boolean> map = new HashMap<>();
	static Deque<String> stack = new LinkedList<>();
	static int max, len;
	static char[] str;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		len = str.length;
		max = 0;
		if (len < 10)
			max = len;
		else
			max += 9 + (len - 9) / 2;

		map.put("0", false);
		for (int i = 1; i <= max; i++)
			map.put(Integer.toString(i), true);

		dfs(0);

		StringBuilder sb = new StringBuilder();
		Iterator<String> it = stack.iterator();
		while (it.hasNext())
			sb.append(it.next()).append(" ");
		System.out.println(sb);
	}

	private static void dfs(int idx) {
//		if (idx >= len) {
//			flag = true;
//			return;
//		}
		try {
			String one = Character.toString(str[idx]);
			if (map.get(one)) {
				stack.add(one);
				map.put(one, false);
				dfs(idx + 1);
				if (flag)
					return;
				stack.removeLast();
				map.put(one, true);
			}
			if (idx == len - 1)
				return;
			String two = Character.toString(str[idx]) + Character.toString(str[idx + 1]);
			if (map.get(two)) {
				stack.add(two);
				map.put(two, false);
				dfs(idx + 2);
				if (flag)
					return;
				stack.removeLast();
				map.put(two, true);
			}
		}catch (NullPointerException e) {
			return;
		} 
		catch (Exception e) {
			flag = true;
			return;
		}
	}
}