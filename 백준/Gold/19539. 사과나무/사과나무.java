import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Queue<Integer> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());
			sum += input;
			pq.add(input);
		}
		if (sum % 3 != 0)
			System.out.println("NO");
		else {
			int one = 0;
			int two = 0;
			while (!pq.isEmpty()) {
				int now = pq.poll();
				if (now >= 3) {
					while (two > 0 && now > 2) {
						now -= 2;
						two--;
					}
					while (one > 0 && now > 1) {
						now -= 1;
						one--;
					}
					
					now %= 3;
				}
				if (now == 2) {
					if (two > 0)
						two--;
					else
						one++;
					continue;
				} else if (now == 1) {
					if (one > 0)
						one--;
					else
						two++;
				}
			}
			if(one + two > 0)
				System.out.println("NO");
			else
				System.out.println("YES");
		}
	}
}
