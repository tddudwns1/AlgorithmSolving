import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] house = new int[n--][9];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 9; i++)
			house[0][i] = 1001;
		for (int i = 0; i < 3; i++)
			house[0][i * 4] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int center = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 3; j++) {
				int line = j * 3;
				int line2 = line + 1;
				int line3 = line + 2;
				house[i + 1][line] = Math.min(house[i][line2], house[i][line3]) + left;
				house[i + 1][line2] = Math.min(house[i][line], house[i][line3]) + center;
				house[i + 1][line3] = Math.min(house[i][line], house[i][line2]) + right;
			}
		}
		for (int i = 0; i < 3; i++)
			house[n][i * 4] = Integer.MAX_VALUE;
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < 9; i++)
			ans = Math.min(ans, house[n][i]);
		System.out.println(ans);
	}
}
