import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			if(Math.abs(o1) == Math.abs(o2)) {
				if(o1 < o2)
					return -1;
				else
					return 1;
			}
			else if(Math.abs(o1) < Math.abs(o2)) {
				return -1;
			}
			else
				return 1;
		});

		for (int tc = 1; tc <= T; tc++) {
			int command = Integer.parseInt(br.readLine());
			if (command == 0)
				if (pq.isEmpty())
					System.out.println(0);
				else
					System.out.println(pq.poll());
			else
				pq.add(command);
		}
	}
}
