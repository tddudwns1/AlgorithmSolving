import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<String> nums = new PriorityQueue<>((o1, o2) -> {
			int len1 = o1.length();
			int len2 = o2.length();
			int shortL = Math.min(len1, len2);
			int difL = Math.abs(len1 - len2);

			if (len1 == len2)
				return o2.compareTo(o1);

			for (int i = 0; i < shortL; i++) {
				if (o1.charAt(i) > o2.charAt(i))
					return -1;
				else if (o1.charAt(i) < o2.charAt(i))
					return 1;
			}

			if (len1 > len2) {
				for (int i = 0; i < difL; i++) {
					if (o1.charAt(i + len2) < o1.charAt(i))
						return 1;
					else if (o1.charAt(i + len2) > o1.charAt(i))
						return -1;
				}
				for (int i = 0; i < shortL; i++) {
					if (o1.charAt(i + difL) > o2.charAt(i))
						return 1;
					else if (o1.charAt(i + difL) < o2.charAt(i))
						return -1;
				}
			} else {
				for (int i = 0; i < difL; i++) {
					if (o2.charAt(i + len1) < o2.charAt(i))
						return -1;
					else if (o2.charAt(i + len1) > o2.charAt(i))
						return 1;
				}
				for (int i = 0; i < shortL; i++) {
					if (o2.charAt(i + difL) > o1.charAt(i))
						return -1;
					else if (o2.charAt(i + difL) < o1.charAt(i))
						return 1;
				}
			}
			return 0;
		});
		for (int i = 0; i < n; i++) {
			nums.add(st.nextToken());
		}
		if (nums.peek().equals("0"))
			System.out.println(0);
		else {
			for (int i = 0; i < n; i++)
				sb.append(nums.poll());
			System.out.println(sb);
		}
	}
}