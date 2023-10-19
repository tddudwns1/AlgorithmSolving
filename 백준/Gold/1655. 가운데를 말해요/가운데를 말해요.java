import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		Queue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		Queue<Integer> big = new PriorityQueue<>();

		small.add(Integer.parseInt(br.readLine()));
		sb.append(small.peek()).append("\n");
		if (n > 1) {
			small.add(Integer.parseInt(br.readLine()));
			big.add(small.poll());
			sb.append(small.peek()).append("\n");
		}

		for (int i = 2; i < n; i++) {
			int now = Integer.parseInt(br.readLine());
			if (big.peek() < now) {
				big.add(now);
				if (i % 2 == 0)
					small.add(big.poll());
			} else {
				small.add(now);
				if (i % 2 == 1)
					big.add(small.poll());
			}
//			System.out.println(small.peek());
			sb.append(small.peek()).append("\n");
		}

		System.out.println(sb);
	}
}