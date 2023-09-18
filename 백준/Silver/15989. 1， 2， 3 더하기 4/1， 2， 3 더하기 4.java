import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		int[][] solution = new int[10001][2];
		solution[2][0] = 1;
		solution[3][0] = 1;
		solution[3][1] = 1;

		for (int i = 4; i <= 10000; i++) {
			solution[i][0] = solution[i - 2][0] + 1;
			solution[i][1] = solution[i - 3][0] + solution[i - 3][1] + 1;
		}

		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(solution[n][0] + solution[n][1] + 1).append("\n");
		}
		System.out.println(sb);
	}
}
