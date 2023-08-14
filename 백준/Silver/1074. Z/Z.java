import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, n);

		find(size, r, c);
		System.out.println(cnt);
	}

	private static void find(int size, int r, int c) {
		if (size == 1)
			return;
		size /= 2;
		if (r < size) {
			if (c < size)
				find(size, r, c);
			else {
				cnt += size * size;
				find(size, r, c - size);
			}
		} else {
			if (c < size) {
				cnt += size * size * 2;
				find(size, r - size, c);
			} else {
				cnt += size * size * 3;
				find(size, r - size, c - size);
			}
		}
	}
}
