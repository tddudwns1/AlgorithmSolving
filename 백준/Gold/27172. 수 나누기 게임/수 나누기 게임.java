import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] position;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean[] numbers = new boolean[1_000_001];
		int[] scores = new int[1_000_001];
		int n = Integer.parseInt(br.readLine());
		int[] players = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(st.nextToken());
			numbers[number] = true;
			players[i] = number;
		}
		
		for(int num : players) {
			for(int i = num * 2; i <= 1_000_000; i += num) {
				if(numbers[i]) {
					scores[i] -= 1;
					scores[num] += 1;
				}
			}
		}
		
		for(int player : players)
			sb.append(scores[player]).append(" ");
		System.out.println(sb);
	}
}
