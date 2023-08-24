import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 종류
		int k = Integer.parseInt(st.nextToken()); // 연속
		int c = Integer.parseInt(st.nextToken()); // 쿠폰

		int cnt = 1;
		int[] counts = new int[d + 1];
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		
		counts[c] = 1;
		for (int i = 0; i < k; i++) {
			int input = Integer.parseInt(br.readLine());
			if (counts[input] == 0)
				cnt++;
			q.add(input);
			q2.add(input);
			counts[input]++;
		}
		int max = cnt;
		
		for(int i = k; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if (counts[input] == 0)
				cnt++;
			q.add(input);
			counts[input]++;
			if(--counts[q.poll()] == 0)
				cnt--;
			max = Math.max(max, cnt);
		}

		for (int i = 0; i < k - 1; i++) {
			int input = q2.poll();
			if (counts[input] == 0)
				cnt++;
			q.add(input);
			counts[input]++;
			if(--counts[q.poll()] == 0)
				cnt--;
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
}
