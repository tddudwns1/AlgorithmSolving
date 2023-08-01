import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ott = new int[11];
		ott[1] = 1;
		ott[2] = 2;
		ott[3] = 4;
		for (int i = 4; i < 11; i++)
			ott[i] = ott[i - 1] + ott[i - 2] + ott[i - 3];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			System.out.println(ott[Integer.parseInt(br.readLine())]);
		}
	}
}