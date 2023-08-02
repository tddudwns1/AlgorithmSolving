import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			String[] str = br.readLine().split(" ");
			int left = Integer.parseInt(str[0]);
			int mid = Integer.parseInt(str[1]);
			int right = Integer.parseInt(str[2]);
			cost[i][0] = Math.min(cost[i - 1][1], cost[i - 1][2]) + left;
			cost[i][1] = Math.min(cost[i - 1][0], cost[i - 1][2]) + mid;
			cost[i][2] = Math.min(cost[i - 1][0], cost[i - 1][1]) + right;
		}
		System.out.println(Math.min(Math.min(cost[n][0], cost[n][1]), cost[n][2]));
	}
}
