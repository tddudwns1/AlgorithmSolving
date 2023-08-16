import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> trucks = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			trucks.add(Integer.parseInt(st.nextToken()));
		for (int i = 0; i < w; i++)
			q.add(null);
		int sum = 0;
		int cnt = w;
		while (!trucks.isEmpty()) {
			cnt++;
			Integer poll = q.poll();
			if(poll != null)
				sum-= poll;
			if (sum + trucks.peek() > l)
				q.add(null);
			else {
				sum += trucks.peek();
				q.add(trucks.poll());
			}
		}
		System.out.println(cnt);
	}
}
