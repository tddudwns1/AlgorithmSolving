import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] triangle = new int[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			String str[] = br.readLine().split(" ");
			for (int j = 1; j <= str.length; j++)
				triangle[i][j] = Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]) + Integer.parseInt(str[j - 1]);
		}
		int max = 0;
		for (int i = 1; i <= n; i++)
			max = Math.max(max, triangle[n][i]);
		System.out.println(max);
	}
}
