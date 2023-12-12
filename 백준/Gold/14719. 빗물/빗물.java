import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[] blocks = new int[w];

		int max = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++)
			if (max < (blocks[i] = Integer.parseInt(st.nextToken())))
				max = blocks[i];

		int puddle = 0;

		for (int i = 1; i <= max; i++) {
			boolean isNotFirst = false;
			int cnt = 0;
			for (int j = 0; j < w; j++) {
				if (blocks[j] < i) {
					cnt++;
					continue;
				}

				if (isNotFirst)
					puddle += cnt;
				isNotFirst = true;
				cnt = 0;
			}
		}
		
		System.out.println(puddle);
	}
}