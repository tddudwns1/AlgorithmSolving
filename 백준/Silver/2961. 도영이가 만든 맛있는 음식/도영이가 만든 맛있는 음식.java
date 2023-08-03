import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] tastes;
	static boolean[] add;
	static int mindif;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		mindif = Integer.MAX_VALUE;
		tastes = new int[n][2];
		add = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tastes[i][0] = Integer.parseInt(st.nextToken());
			tastes[i][1] = Integer.parseInt(st.nextToken());
		}
		comb(0);
		System.out.println(mindif);
	}

	private static void comb(int def) {
		if (def == n) {
			int sumS = 1;
			int sumB = 0;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (add[i]) {
					sumS *= tastes[i][0];
					sumB += tastes[i][1];
					cnt++;
				}
			}
			if (cnt != 0)
				mindif = Math.min(mindif, Math.abs(sumB - sumS));
			return;
		}
		add[def] = true;
		comb(def + 1);
		add[def] = false;
		comb(def + 1);
	}
}
