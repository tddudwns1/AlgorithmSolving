import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Line {
		int start;
		int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				if(o1.end != o2.end)
					return o1.end - o2.end;
				return o1.start - o2.start;
			}
		});
		Queue<Line> contain = new PriorityQueue<>(new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				if(o1.start != o2.start)
					return o1.start - o2.start;
				return o1.end - o2.end;
			}
		});

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a < b)
				pq.add(new Line(a, b));
			else
				pq.add(new Line(b, a));
		}
		int d = Integer.parseInt(br.readLine());

		int ans = 0;
		while (true) {
			if (pq.isEmpty() || pq.peek().end - pq.peek().start <= d)
				break;
			pq.poll();
		}
		while (!pq.isEmpty()) {
			Line e = pq.poll();
			if (e.end - e.start > d)
				continue;
			if (!contain.isEmpty() && contain.peek().start + d < e.end) {
				int maginot = e.end - d;
				while (true) {
					if (contain.isEmpty() || maginot <= contain.peek().start)
						break;
					contain.poll();
				}
			}
			contain.add(e);
			ans = Math.max(ans, contain.size());
		}
		System.out.println(ans);
	}
}
