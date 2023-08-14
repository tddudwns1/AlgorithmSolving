import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][][] print;
	static int k;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		k = Integer.parseInt(br.readLine());
		int 가로반전 = 0;
		int 세로반전 = 0;
		String 가로 = "L";
		String 세로 = "U";
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * k; i++) {
			String str = st.nextToken();
			if ("RL".contains(str)) {
				if (str.equals("L") && 가로.equals("R")) {
					가로반전 = 0;
					가로 = "L";
				} else if (str.equals("R") && 가로.equals("L")) {
					가로반전 = 1;
					가로 = "R";
				}
			} else {
				if (str.equals("U") && 세로.equals("D")) {
					세로반전 = 0;
					세로 = "U";
				} else if (str.equals("D") && 세로.equals("U")) {
					세로반전 = 1;
					세로 = "D";
				}
			}
		}
		print = new int[4][2][2];
		print[0][0] = new int[] { 0, 1 };
		print[0][1] = new int[] { 2, 3 };
		print[1][0] = new int[] { 1, 0 };
		print[1][1] = new int[] { 3, 2 };
		print[2][0] = new int[] { 2, 3 };
		print[2][1] = new int[] { 0, 1 };
		print[3][0] = new int[] { 3, 2 };
		print[3][1] = new int[] { 1, 0 };
		int h = Integer.parseInt(br.readLine());
		int target = 0;
		for (int i = 0; i < 4; i++)
			if (print[i][세로반전][가로반전] == h) {
				target = i;
				break;
			}
		int times = (int) Math.pow(2, k) / 2;
		for (int i = 0; i < times; i++) {
			for (int j = 0; j < times; j++) {
				sb.append(print[target][0][0]).append(" ").append(print[target][0][1]).append(" ");
			}
			sb.append("\n");
			for (int j = 0; j < times; j++) {
				sb.append(print[target][1][0]).append(" ").append(print[target][1][1]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
