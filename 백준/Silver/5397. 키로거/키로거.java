import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			List<Character> pw = new LinkedList<>();
			char[] input = br.readLine().toCharArray();
			int times = input.length - 1;
			int cur = 0;
			for (int i = 0; i <= times; i++) {
				int len = pw.size();
				if (input[i] == '<') {
					if (cur > 0)
						cur--;
				} else if (input[i] == '>') {
					if (cur < len)
						cur++;
				} else if (input[i] == '-') {
					if (cur > 0)
						pw.remove(--cur);
				} else
					pw.add(cur++, input[i]);
			}
			Iterator<Character> it = pw.iterator();
			while(it.hasNext())
				sb.append(it.next());
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
